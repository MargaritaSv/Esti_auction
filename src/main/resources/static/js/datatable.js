$(document).ready(function () {
    // var data = JSON.stringify('th:object="${viewModel}"');
    // console.log(data)
    var table = $('#userTable').DataTable({
        "ajax": "/department/users/json",
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "columns": [
            {"data": "id", "name": "id"},
            {"data": "fullName"},
            {"data": "birthday"},
            {"data": "gender"},
            {"data": "primaryAddress"},
            {"data": "secondAddress"},
            {"data": "country"},
            {"data": "email"},
            {"data": "cardNumber"},
            {
                'data': 'Action', 'render': function (data, type, row, meta) {
                    return '<div class="row justify-content-center"><input type="button" id="' + row.id + '" value="Edit" class="btn btn-outline-warning btnEdit" /> </div>' +
                        '<div class="row justify-content-center"><input type="button" id="' + row.id + '" value="Delete" class="btn btn-outline-dark btnDelete" /></div>';
                }

            },
        ]
    })

    $("#userTable tbody").on("click", ".btnEdit", function () {
        // var data = table.row(this).data();
//var x = table.row(this).id.text();
        //  alert(data);
        //   $.ajax({
        //       url: "user/edit/1",
        //       type: "GET",
        //       data: "id"
        //   });
    });
});