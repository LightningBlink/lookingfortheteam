app.directive('login', function ($compile) {
    return {
        restrict: 'A',
        replace: true,
        link: function (scope, ele) {

            check(scope.authenticated);

            scope.$watch('authenticated', function () {
                check(scope.authenticated);
                $compile(ele.contents())(scope);
            });


            function check(auth) {
                auth ? ele.html(scope.trustHtml('login.html')) : ele.html(scope.trustHtml('logout.html'));
            }
        }
    };
});


var repeatDirective = function() {
    return {
        restrict: 'A',
        require: '?ngModel',
        link: function(scope, elm, attr, ctrl) {
            var el = angular.element("#repeat" + attr.id);
            attr.repeat = true; // force truthy in case we are on non input element

            ctrl.$validators.repeat = function(modelValue, viewValue) {
                return !attr.repeat || el.val() == elm.val();
            };

            scope.$watch(function() {
                return el.val();
            },function(){
                ctrl.$validate();

            });

            attr.$observe('repeat', function() {
                ctrl.$validate();
            });
        }
    };
};


app.directive('compile', function ($compile) {
    // directive factory creates a link function
    return function (scope, element, attrs) {
        scope.$watch(
            function (scope) {
                // watch the 'compile' expression for changes
                return scope.$eval(attrs.compile);
            },
            function (value) {
                // when the 'compile' expression changes
                // assign it into the current DOM
                element.html(value);

                // compile the new DOM and link it to the current
                // scope.
                // NOTE: we only compile .childNodes so that
                // we don't get into infinite loop compiling ourselves
                $compile(element.contents())(scope);
            }
        );
    };
});

app.directive('nationpicker', function ($parse) {
    // The directive has been rewritten to work properly
    return {
        restrict: 'A', // It is an attribute
        require: '?ngModel', // It uses ng-model binding
        scope: {
            ngModel: '='
        },
        link: function (scope, elem, attrs, ctrl) {
            // Add the required classes
            elem.addClass('bfh-countries');
            elem.addClass('bfh-selectbox');

            if (!scope.ngModel) {
                scope.ngModel = 'US';
            }

            // First we initialize the selectbox with the desired options
            elem.bfhselectbox({
                filter: (elem.attr('data-filter') == 'true') ? true : false
            }).on('change.bfhselectbox', function () {
                // Listen for the change event and update the bound model
                return scope.$apply(function () {
                    return scope.ngModel = elem.val();
                });
            });

            scope.$watch(function () {
                elem.val(scope.ngModel ? scope.ngModel : "US");
            });

            // Initialize the countries with the desired options
            return elem.bfhcountries({
                flags: (elem.attr('data-flags') == 'true') ? true : false,
                country: scope.ngModel || 'AT'
            });
        }
    };
});

app.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});

app.directive('show', function ($timeout) {

    return function (scope, elem, attr) {
        scope.$watch(function () {
                return scope.message.isCollapsed
            },
            function (e) {
                if (!scope.message.isCollapsed) {
                    $timeout(function () {
                        elem[0].scrollTop = elem[0].scrollHeight;
                    }, 200);

                }
            });
    }

});


