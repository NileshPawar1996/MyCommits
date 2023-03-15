const StudentRow = (props) => {
    const { student } = props
    return (
        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        {student.id}
        {student.firstName}
        {student.lastName}
    </button>
    )
}
export default StudentRow