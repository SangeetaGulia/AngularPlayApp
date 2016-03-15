

angular.module('myApp').controller('InternCtrl', function($scope,$http) {

    $http.get('/myIntern').success(function(data){

        $scope.interns=data.interns;
    });


$scope.displayForm=function(){
 window.location='/form'
};

$scope.deleteById=function(){
 var data1={"id":$scope.delete};

 $http({
 method:'POST',
 url:'/deleteIntern',
 data: JSON.stringify(data1),
 contentType: 'application/json',
 dataType:'json'
 });

}


});

/*
angular.module('myApp').controller('InternAddCtrl', function($scope,$http) {
    // get names using AngularJS AJAX API

    $scope.add=function(){

var data1 = {"name":$scope.name,"email":$scope.email,"mobile":$scope.mobile,"address":$scope.address,"emergencyContact":$scope.emergency,"id":$scope.id};

//window.location='/addIntern'
$http({
method:'POST',
url:'/addIntern',
data: JSON.stringify(data1),
contentType: 'application/json',
dataType:'json'
});


};
});
*/