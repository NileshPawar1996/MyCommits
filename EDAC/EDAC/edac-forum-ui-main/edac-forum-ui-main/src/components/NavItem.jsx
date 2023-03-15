import { Link, useLocation } from "react-router-dom";

export default function NavItem({ path, parent }) {
  const { pathname } = useLocation();

  if (path.type == "simple")
    return <li className="my-nav-path">
      <Link to={path.to} className={`my-nav-link gap-3 ${(pathname == path.to) ? "my-nav-active" : ""}`}>
        <i className={path.icon} />
        {path.text}
      </Link>
    </li>

  if (path.type == "nested")
    return <li className="my-nav-path">
      <Link to={path.to} className="my-accordian-path">
        <div className={`my-nav-link drop-nav ${(pathname == path.to) ? "my-nav-active" : ""}`}
          // data-bs-toggle="collapse"
          data-bs-target={`#menu${path.text}`}
        >
          <span className="d-flex gap-3">
            <i className={path.icon} />
            {path.text}
          </span>
          <i className="bi-chevron-right" />
        </div>
        <div
          id={`menu${path.text}`} className={` accordion-collapse ${(pathname.includes(path.to)) ? "" : "collapse"}`} data-bs-parent={`#${parent}`}>
          <div class="my-accordian-body">
            {path.paths.map(innerPath => {
              return <Link className={`my-nav-link nested-nav-link ${(pathname == path.to + innerPath.to) ? " my-nav-active" : ""}`}
                to={path.to + innerPath.to}>
                <i className={innerPath.icon} />
                {innerPath.text}</Link>
            })}
          </div>
        </div>
      </Link>
    </li >
}