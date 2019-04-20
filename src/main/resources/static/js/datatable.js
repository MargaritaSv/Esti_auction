//
//
// $(document).ready(function () {
//
//     // $.ajaxSetup({
//     //     dataType: "json",
//     //     beforeSend: function(xhr, settings){
//     //         var csrftoken = $.cookie('CSRF-TOKEN');
//     //         xhr.setRequestHeader("X-CSRF-TOKEN", csrftoken);
//     //     },
//     // });
//
//
//
//
//     var table = $('#userTable').DataTable({
//
//
//         "processing": true,
//         "serverSide": true,
//         "ajax": {
//             "url": "/department/users",
//             "dataType": "jsonp"
//         },
//         // "ajax" : {
//         //     "url" : "/department/users",
//         //     "type" : "POST",
//         //     "dataSrc": ""
//         // },
//
//         "aoColumns": [
//             {"mData": "id"},
//             {"mData": "firstName"},
//             {"mData": "birthday"},
//             {"mData": "gender"},
//             {"mData": "primaryAddress"},
//             {"mData": "secondAddress"},
//             {"mData": "email"}
//         ],
//         "paging":true,
//         "pageLength":20,
//         "ordering":true,
//         "order":[0,"asc"]
//     })
//     console.log(table);
// });



$(document).ready( function () {
    var table = $('#userTable').DataTable({
        "sAjaxSource": "/department/users",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
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
    console.log(table);
});