const wrapData = (data) => {
  return { data, status: "success" };
}

const wrapError = (err) => {
  return { data: err, status: "error" };
}

const daoCaller = async (callback, args) => {
  let response = {};
  await callback(args)
    .then((data) => {
      // console.log(data);
      if (data.status != undefined)
        throw data;
      data.status = "200";
      // console.log("data: ", data);
      response = wrapData(data);
    })
    .catch((err) => {
      err.status == err.status || 404;
      console.log("error: ", err);
      response = wrapError(err);
    })

  return response;
}

module.exports = { wrapData, wrapError, daoCaller }