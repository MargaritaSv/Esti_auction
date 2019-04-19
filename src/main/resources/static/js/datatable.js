$(document).ready(function () {
    var table = $('#userTable').DataTable({
        "sAjaxSource": "/department/users",
        "sAjaxDataProp": "",
        "serverSide": true,
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "firstName"},
            {"mData": "birthday"},
            {"mData": "gender"},
            {"mData": "primaryAddress"},
            {"mData": "secondAddress"},
            {"mData": "email"}
        ]
    })
});