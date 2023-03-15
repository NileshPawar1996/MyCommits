package com.app.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.app.Service.AttendanceService;
import com.app.Service.StudentService;
import com.app.dtos.AttendanceDTO;
import com.app.dtos.DataDTO;
import com.app.dtos.ModuleSessionsDTO;
import com.app.pojos.Attendance;
import com.app.pojos.Student;
import com.app.utils.Message;
import com.opencsv.CSVReader;
import com.app.dtos.SessionDTO;
// by AdityaS
@CrossOrigin
@RequestMapping("/attendance")
@RestController
public class AttendanceController {
	@Autowired
	AttendanceService attendanceService;
	
	@Autowired
	StudentService studentService;

	@GetMapping("/studentId/{studentId}")
	public ResponseEntity<Object> getAttendanceByStudentId(@PathVariable Long studentId){
	
	try {
		List<Attendance> foundList = attendanceService.getAttendanceByStudentId(studentId);
		if(foundList!=null) {
			List<AttendanceDTO>returnList = new ArrayList<>();
			for(Attendance a: foundList) {
				returnList.add(new AttendanceDTO(a.getId(), a.getStudent().getId(), a.getSessionId(), a.getDuration()));
			}
			return Message.getSuccessMessage(returnList, HttpStatus.OK);
			
		}else
			return Message.getRuntimeErrorMessage("Student not found", HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/studentIdSessionId/{studentId}/{sessionId}")
	public ResponseEntity<Object> getAttendanceByStudentIdAndSessionId(@PathVariable Long studentId, @PathVariable Long sessionId){
	
	try {
		List<Attendance> foundList = attendanceService.getByStudentIdAndSessionId(studentId,sessionId);
		if(foundList!=null) {
			List<AttendanceDTO>returnList = new ArrayList<>();
			for(Attendance a: foundList) {
				returnList.add(new AttendanceDTO(a.getId(), a.getStudent().getId(), a.getSessionId(), a.getDuration()));
			}
			return Message.getSuccessMessage(returnList, HttpStatus.OK);
			
		}else
			return Message.getRuntimeErrorMessage("Student not found", HttpStatus.NOT_FOUND);
		}catch(Exception e) {
		return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//get all modules
	//get all sessions of a module
	// get the attendance of that session
	//compare the duration and store the status
	
//	public List someFunction() {
//		
//		RestTemplate restTemplate = new RestTemplate();
//        String apiUrl = "http://localhost:8080/student/";
//        Object response = restTemplate.getForObject(apiUrl,Object);
//        
//        return null;
//	}
	

	
	@PostMapping("/uploadFile/{sessionId}")
	public ResponseEntity<Object> InsertAttendanceFromFile(@RequestBody MultipartFile file, @PathVariable Long sessionId )
	{
//	System.out.println(file.getClass());
	try {
			List<Attendance> attList = csvToList(file,sessionId);
			attendanceService.saveAllAttendance(attList);
			return Message.getSuccessMessage("Attendance Saved Successfully", HttpStatus.OK);
			
		}catch(Exception e) {
			return Message.getErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	
    public List<Attendance> csvToList( MultipartFile file, Long sessionId) throws Exception{
        
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
    	List<Attendance> attendanceList = new ArrayList<>();
    	String line;
    	int count = 0;
    	while ((line = br.readLine()) != null) {  
    	       
    			
    			if(count==0) {
    				count++;
    				continue;
    			}
    			else{
    				
    	    	   // split on comma(',')
    	    	   String[] row = line.split(",");  

        	       // create attendance object to store values  
        	       Attendance att = new Attendance();  
        	       
        	       Student st = studentService.getStudentByStudentId(Long.parseLong(row[0]));
        	         
        	       att.setStudent(st);
        	       att.setSessionId(sessionId);
        	       att.setDuration(Integer.parseInt(row[1]));
        	       
        	       attendanceList.add(att);
        	       Object o = new Object();
    	       }
    			
    	}
    	br.close();       
        return attendanceList;
    }
    
    @GetMapping("/test")
    public Map<Long, Double> getSessionByBatchId(){
    	Long studentId =(long) 1;
    	
    	List<ModuleSessionsDTO> allSessionOfAllModules = moduleSessionsFromAPI();
    	//here we get a list of the ModuleSessionDTO in which we have module details 
    	//and a list of all sessions of that module
    	Map<Long, Double> mapOfTotalAttendancePerModule = new HashMap<>();
    	for(int i=0;i< allSessionOfAllModules.size();i++) {
    		ModuleSessionsDTO moduleSessions = allSessionOfAllModules.get(i);
    		//getting details of one module and a list of all sessions of that module
    		
    		Double presenceForTheModule = 0.0;
    		Double attCounter =0.0;
    		
    		List<SessionDTO> listOfSessions = moduleSessions.getListOfSessions();
    		StringBuilder stringForNativeQuery = new StringBuilder();
    		for(int j=0;j<listOfSessions.size();j++) {
    			//iterating over the list of sessions of a module and appending the 
    			//sessionId of each session in the string to use in the native query
    			
    			SessionDTO session = listOfSessions.get(j);
    			stringForNativeQuery.append(session.getId());
    			stringForNativeQuery.append(",");
    			
    			if(j==listOfSessions.size()-1) {
    				stringForNativeQuery.deleteCharAt(stringForNativeQuery.length()-1);
    				// to remove the last comma from the string
    			}    			
    		}
    		List<Attendance> listOfAttendanceFound= attendanceService.findByStudentIdAndMultipleSessionId(studentId, stringForNativeQuery);
    		System.out.println(listOfAttendanceFound);
    		
    		//comparing the duration in sessionDTO with the duration in attendance found and calculating the status 
    		
    		for(int k=0;k<listOfSessions.size();k++) {
    			if((listOfAttendanceFound.get(i).getDuration()/listOfSessions.get(i).getSessionDuration())>0.8) {
    				attCounter++;
    			}
    			else if((listOfAttendanceFound.get(i).getDuration()/listOfSessions.get(i).getSessionDuration())>0.5) {
    				attCounter = attCounter + 0.5;
    			}
    		}
    		presenceForTheModule = (attCounter/listOfSessions.size())*100;
    		mapOfTotalAttendancePerModule.put(moduleSessions.getModuleId(), presenceForTheModule);
    	}
    	
    	return mapOfTotalAttendancePerModule;
    }
    
    
    public List<ModuleSessionsDTO> moduleSessionsFromAPI(){
    	//Fetching a list of all the sessions of all the modules from the management service and catching it in a Object class 
    	ResponseEntity<DataDTO> respEtt;
    	RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://13.233.107.158:8080/manage/session/DAC/2";
        respEtt = restTemplate.getForEntity(apiUrl, DataDTO.class);
        System.out.println("");
    	
        //This method will return a list of all the sessions per module (ModuleSessionDTO)
        List<ModuleSessionsDTO> moduleSessions = respEtt.getBody().getData();
        
        return moduleSessions;
    }
    
    
}
