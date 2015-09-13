/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*publicar comentario*/
$(function () {
    $('.panel-google-plus > .panel-footer > .input-placeholder, .panel-google-plus > .panel-google-plus-comment > .panel-google-plus-textarea > button[type="reset"]').on('click', function (event) {
        var $panel = $(this).closest('.panel-google-plus');
        $comment = $panel.find('.panel-google-plus-comment');

        $panel.toggleClass('panel-google-plus-show-comment');

        if ($panel.hasClass('panel-google-plus-show-comment')) {
            $comment.find('textarea').focus();
        }
    });
});

/*publicar topico*/
$(function () {
    $('.panel-google-plus > .panel-footer > .input-topico, .panel-google-plus > .panel-google-plus-comment > .panel-google-plus-textarea > button[type="cancel"]').on('click', function (event) {
        var $panel = $(this).closest('.panel-google-plus');
        $comment = $panel.find('.panel-google-plus-comment');

        $panel.toggleClass('panel-google-plus-show-comment');

        if ($panel.hasClass('panel-google-plus-show-comment')) {
            $comment.find('textarea').focus();
        }
    });
});

/*Comentarios*/
$(function () {
    $('.panel-comentarios > .panel-footer > .input-comentarios, .panel-comentarios > .panel-google-plus-comments > button[type="cancel"]').on('click', function (event) {
        var $panel = $(this).closest('.panel-comentarios');
        $comment = $panel.find('.panel-google-plus-comments');
        
        $panel.toggleClass('panel-google-plus-show-comments');
    });
});

/*Arquivo*/
$(function () {
    $('.panel-google-plus > .panel-footer > .input-arquivo, .panel-google-plus > .panel-google-plus-arquivo > .panel-google-plus-textarea-arquivo > button[type="cancel"]').on('click', function (event) {
        var $panel = $(this).closest('.panel-google-plus');
        $comment = $panel.find('.panel-google-plus-arquivo');

        $comment.find('.btn:first-child').addClass('disabled');
        $comment.find('.textarea-arquivo').val('');

        $panel.toggleClass('panel-google-plus-show-arquivo');

        if ($panel.hasClass('panel-google-plus-show-arquivo')) {
            $comment.find('.textarea-arquivo').focus();
        }
    });
});