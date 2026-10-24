angular.module('projectFilters', []).filter('dateLocale', function() {
    var dateTime;
    function size(value){
        if(value.toString().length == 1){
            return "0" + value.toString();
        }
        return value;
    }

    return function(input) {
        dateTime =  new Date(input.year, input.monthValue, input.dayOfMonth, input.hour, input.minute);
        dateTime =  new Date(dateTime.getTime() - (dateTime.getTimezoneOffset() * 60000));
        return dateTime.getFullYear() + "-" + size(dateTime.getMonth()) + "-" + size(dateTime.getUTCDate())  + " " + size(dateTime.getHours()) + ":" + size(dateTime.getMinutes());
    };
});
