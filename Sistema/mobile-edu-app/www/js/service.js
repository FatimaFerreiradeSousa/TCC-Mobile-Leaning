angular.module('starter').factory('fac', function() {
    
    var alunos = [];

    var obj =  {
      salvar: function(al) {
        alunos.push(al)
        alert('Salvo' +alunos.length);
      },

      getAluno: function(index){        
        for (var i = 0; i < alunos.length; i++) {
          if (alunos[i].codigo === parseInt(index)) {
            return alunos[i];
          }
        }
        return null;
      }
    }

    return obj;

})
