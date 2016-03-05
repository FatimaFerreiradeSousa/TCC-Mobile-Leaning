angular.module('starter').factory('fac', function($state) {
   var clients = [];

    var obj =  {
      salvar: function() {
        alert("factory");
        $state.go("home");
      }
    }

    return obj;

})
