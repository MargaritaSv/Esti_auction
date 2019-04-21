$(document).ready(function () {
    var data = JSON.stringify('th:object="${viewModel}"');
    console.log(data)
    var table = $('#userTable').DataTable({
        "ajax": "/department/users/json",
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "columns": [
            {"data": "id"},
            {"data": "firstName"},
            {"data": "birthday"},
            {"data": "gender"},
            {"data": "primaryAddress"},
            {"data": "secondAddress"},
            {"data": "email"}
        ]
    })
});