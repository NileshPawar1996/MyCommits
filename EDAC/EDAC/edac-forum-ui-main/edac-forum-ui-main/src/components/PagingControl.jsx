export default function PagingControl({ pageNo, setPageNo, hasMore }) {
  return <div className="d-flex gap-2 align-items-center">
    <button className="btn btn-sm btn-outline-primary round" disabled={pageNo <= 0}
      onClick={() => { setPageNo((prev) => prev > 0 ? prev - 1 : 0); }}
    ><i className="bi-chevron-left" /></button>
    <span className="btn">{pageNo + 1}</span>
    <button className="btn btn-sm btn-outline-primary round" disabled={!hasMore}
      onClick={() => { setPageNo((prev) => prev + 1); }}
    ><i className="bi-chevron-right" /></button>
  </div>
}