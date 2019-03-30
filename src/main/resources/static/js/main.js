$(function () {

    $('[data-tab]').on('click', function (e) {
        $(this).addClass('active').siblings('[data-tab]').removeClass('active')
        $(this).siblings('[data-content=' + $(this).data('tab') + ']').addClass('active').siblings('[data-content]').removeClass('active')
        e.preventDefault()
    })

})