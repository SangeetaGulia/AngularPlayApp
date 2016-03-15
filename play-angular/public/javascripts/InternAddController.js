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

alert("Intern Added")
};

$scope.displayEmployee=function(){
window.location='/intern';
};

});