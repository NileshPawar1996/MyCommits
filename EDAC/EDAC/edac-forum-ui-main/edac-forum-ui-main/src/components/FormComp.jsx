export default function FormComp({ fields, handleChange }) {
  return <>
    {fields.map(field => {
      return <div className="form-floating mb-3">
        <input type={field.type} id={field.name + "-input"} name={field.name}
          value={field.value}
          className="form-control"
          onChange={(e) => { handleChange(e) }} />
        <label htmlFor={field.name + "-input"}>{field.label}</label>
      </div>
    })}
  </>
}