const AccordianItem = (prop)=>{
    return (
    <div class="accordion-item">
        <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              <h4>{prop.id}</h4>
              <h4>{prop.firstName}</h4>
              <h4>{prop.sirName}</h4>
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
            <div class="accordion-body">
                <strong>Other details about {prop.firstName}</strong> He's a good guy.
          </div>
        </div>
    </div>
    );
}
export default AccordianItem;