/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Postagens*/
$(function () {

    $('#login-form-topico').click(function (e) {
        $("#topico-form").delay(100).fadeIn(100);
        $("#arquivo-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-arquivo').click(function (e) {
        $("#arquivo-form").delay(100).fadeIn(100);
        $("#topico-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});
