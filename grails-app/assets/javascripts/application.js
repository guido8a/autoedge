// // This is a manifest file that'll be compiled into application.js.
// //
// // Any JavaScript file within this directory can be referenced here using a relative path.
// //
// // You're free to add application-wide JavaScript to this file, but it's generally better
// // to create separate JavaScript files as needed.
// //
// //= require jquery
// //= require webjars/jquery/%/dist/jquery.js
// //= require webjars/bootstrap/%/dist/js/bootstrap.bundle.js
// //= require_self

// //= require_tree .

// if (typeof jQuery !== 'undefined') {
//     (function($) {
//         $('#spinner').ajaxStart(function() {
//             $(this).fadeIn();
//         }).ajaxStop(function() {
//             $(this).fadeOut();
//         });
//     })(jQuery);
// }

// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
//= require webjars/jquery/%/dist/jquery.js
//= require webjars/bootstrap/%/dist/js/bootstrap.bundle.js
//= require bootbox.min
//= require_self

// Nota: Eliminamos require_tree para tener control total del orden.
// Si tienes scripts propios, inclúyelos uno a uno aquí abajo si es necesario.

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ready(function() {
            // Corrección para versiones modernas de jQuery (.ajaxStart se asocia al documento)
            $(document).ajaxStart(function() {
                $('#spinner').fadeIn();
            }).ajaxStop(function() {
                $('#spinner').fadeOut();
            });
        });
    })(jQuery);
}

function cargarLoader(texto){

    var dialog = bootbox.dialog({
        message: '<p class="text-center mb-0" style="font-size: 14px"><i class="fa fa-3x fa-spin fa-cog"></i>' + texto + '</p>',
        closeButton: false
    });

    return dialog

}