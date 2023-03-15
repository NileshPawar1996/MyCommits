export default function DropDownNavItem({ kids }) {
  return <li className="nav-item">
    <div class="accordion" id="accordionExample">
      <Link to="/forum" class="accordian-item">
        <div className="accordion-button collapsed"
          data-bs-toggle="collapse" data-bs-target="#collapseOne">
          Forum
        </div>
        <div id="collapseOne" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
          <div class="accordion-body p-0 d-flex flex-column">
            <Link className="btn" to="/forum/1/S">Forum 1</Link>
            <Link className="btn" to="/forum/2/S">Forum 2</Link>
          </div>
        </div>
      </Link>
    </div>
  </li>
}