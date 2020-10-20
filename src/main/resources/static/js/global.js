if (!String.prototype.format) {
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined'
                ? args[number]
                : match
                ;
        });
    };
}
if (!String.prototype.formatStringByObject) {
    String.prototype.formatStringByObject = String.prototype.formatStringByObject ||
        function () {
            "use strict";
            var str = this.toString();
            if (arguments.length) {
                var t = typeof arguments[0];
                var key;
                var args = ("string" === t || "number" === t) ?
                    Array.prototype.slice.call(arguments)
                    : arguments[0];

                for (key in args) {
                    str = str.replace(new RegExp("\\{" + key + "\\}", "gi"), args[key]);
                }
            }

            return str;
        };
}
if (!String.prototype.formatArray) {
    String.prototype.formatArray = function (args) {
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined'
                ? args[number]
                : match
                ;
        });
    };
}

if (!String.prototype.replaceAll) {

    String.prototype.replaceAll = function (find, replace) {
        var str = this;
        return str.replace(new RegExp(find.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&'), 'g'), replace);
    };
}

jQuery.fn.outerHTML = function (s) {
    return s
        ? this.before(s).remove()
        : jQuery("<p>").append(this.eq(0).clone()).html();
};

$.fn.extend({
    disableSelection: function () {
        this.each(function () {
            if (typeof this.onselectstart != 'undefined') {
                this.onselectstart = function () {
                    return false;
                };
            } else if (typeof this.style.MozUserSelect != 'undefined') {
                this.style.MozUserSelect = 'none';
            } else {
                this.onmousedown = function () {
                    return false;
                };
            }
        });
    }
});


function executeFunctionByName(fu1nctionName, context /*, args */) {
    var args = [].slice.call(arguments).splice(2);
    var namespaces = functionName.split(".");
    var func = namespaces.pop();
    for (var i = 0; i < namespaces.length; i++) {
        context = context[namespaces[i]];
    }
    return context[func].apply(context, args);
}

function insertParam(url, key, value) {
    key = encodeURI(key);
    value = encodeURI(value);

    var kvp = url.substr(1).split('&');

    var i = kvp.length;
    var x;
    while (i--) {
        x = kvp[i].split('=');

        if (x[0] == key) {
            x[1] = value;
            kvp[i] = x.join('=');
            break;
        }
    }

    if (i < 0) {
        kvp[kvp.length] = [key, value].join('=');
    }

    //this will reload the page, it's likely better to store this until finished
    return kvp.join('&');
}

var delay = (function () {
    var timer = 0;
    return function (callback, ms) {
        clearTimeout(timer);
        timer = setTimeout(callback, ms);
    };
})();


window.onresize = function (event) {
    resizeDiv();
};

function resizeDiv() {
    vpw = $(window).width();
    vph = $(window).height();
    $(".automaticWindowHeigth").css({'height': vph - 300 + 'px'});
}

// $(function () {
//
//     $('.dropdown-menu.keep-option').on('hide.bs.dropdown', function () {
//         return false;
//     });
//     $('.unselectable').disableSelection();
//     resizeDiv();
//
//
// });

// ___________________


function executeFunctionByName(fu1nctionName, context /*, args */) {
    var args = [].slice.call(arguments).splice(2);
    var namespaces = functionName.split(".");
    var func = namespaces.pop();
    for (var i = 0; i < namespaces.length; i++) {
        context = context[namespaces[i]];
    }
    return context[func].apply(context, args);

    function insertParam(url, key, value) {
        key = encodeURI(key);
        value = encodeURI(value);

        var kvp = url.substr(1).split('&');

        var i = kvp.length;
        var x;
        while (i--) {
            x = kvp[i].split('=');

            if (x[0] == key) {
                x[1] = value;
                kvp[i] = x.join('=');
                break;
            }
        }

        if (i < 0) {
            kvp[kvp.length] = [key, value].join('=');
        }

        //this will reload the page, it's likely better to store this until finished
        return kvp.join('&');
    }
}

/*======================================================================================================*/

var loadingTemplate = null;
var datatable = null;
Dropzone.autoDiscover = false;

// function startProcessing() {
//     $('#upload-container').hide();
//     // $('#upload-container').fadeOut('slow');
//
//     $('#calculating-molecular-descriptors').fadeIn('slow');
//     $('#predicting-data').fadeIn('slow');
// }

function initializeCheckState(id) {
// $('#text-information').hide();
    // $('#predicting-data').hide();
    $('#upload-container').hide();
    $('.search_input').addClass('disabled').attr('disabled', 'disabled');
    $('.searchbar').addClass('disabled');
    $('#calculate-one-smile-button').addClass('disabled').attr('disabled', 'disabled');
    $('#calculating-molecular-descriptors').addClass('disabled').attr('disabled', 'disabled');
    $('#upload-container').hide();
    $('#calculating-molecular-descriptors').fadeIn('slow');
    $('#progress').fadeIn('slow');
    checkState(id)
    if (datatable != null) {
        $(".loadingData").show()
    }

}

var updateHottieTimeOut
var checkStateTimeout
function updateHottie() {

    if (datatable != undefined)
        datatable.columns().every(function () {
            this.nodes().to$().each(function () {

                if ($.isNumeric($(this).text()) && !$(this).hasClass('number'))
                    $(this).addClass('number');

                // if ($(this).text() == 'sometext') {
                // } else {
                //     $(this).next('td').addClass('highlight');
                // }
            });
        });

    $("#datatable_wrapper .dataTable td.number").hottie({
        preMin: 0,
        preMax: 1,
        colorArray: [
            "#FFF",
            "#edfbda",
            "#FBE983",
            "#63BE7B"

        ],
        nullColor: "#FFF"
    });
}


function finaliceAnalysis(response) {

    $('#upload-container').fadeIn('slow');


    if (response != undefined && response.data != undefined) {
        $('#results-section').fadeIn('slow');
        $('#text-information').hide();
    } else {

    }
    $('#progress').hide();
    $('#predicting-data').hide();
    $('#calculating-molecular-descriptors').hide();


    if (response != undefined && response.data != undefined) {
        if (datatable != undefined) {
            datatable.clear().destroy();
            $("#datatable").remove()
            $("#datatable-container").append('<table id="datatable" class="table table-striped table-bordered hover"></table>')
        }

        datatable = $('#datatable')
            .on('column-visibility.dt', function (e, settings, column, state) {
                if (updateHottieTimeOut != undefined)
                    clearTimeout(updateHottieTimeOut);

                updateHottieTimeOut = setTimeout(function () {
                    updateHottie();
                }, 500);

            }).on('page.dt', function (e, settings, column, state) {
                if (updateHottieTimeOut != undefined)
                    clearTimeout(updateHottieTimeOut);

                updateHottieTimeOut = setTimeout(function () {
                    updateHottie();
                }, 500);


            }).on('length.dt', function (e, settings, column, state) {
                if (updateHottieTimeOut != undefined)
                    clearTimeout(updateHottieTimeOut);

                updateHottieTimeOut = setTimeout(function () {
                    updateHottie();
                }, 500);


            })


            .DataTable({
                data: response.data,
                columns: response.columns,
                // responsive: true,
                destroy: true,
                pageLength: 50,
                lengthMenu: [[10, 50, 100, -1], [10, 50, 100, "All"]],
                dom: 'Blfrtip',
                fixedHeader: true,
                autoWidth: true,
                // responsive: true,
                deferRender: true,
                processing: true,
                serverSide: false,
                bAutoWidth: true,

                scrollX: true,
                scrollY: "300px",
                scrollCollapse: true,
                // deferRender:    true,
                // scroller:       true,
                fixedColumns: {
                    leftColumns: 1,
                    // rightColumns: 1
                },
                columnDefs: [

                    {
                        targets: -1,
                        visible: false
                    },
                    {
                        targets: 0,
                        render: function (data, type, full, meta) {
                            // console.log(meta)
                            // console.log(full)
                            //
                            // if(data.length>50)
                            // {
                            //     data=data.substring(0,50)+"..."
                            // }
                            return "<div  class='smileColumn' data-content='" + data + "'>" + data + "</div>";
                        },
                    }
                ],
                buttons: [
                    'copy',
                    'csv',
                    {
                        extend: 'excel',
                        title: 'Smile Predictions',
                        filename: 'SmilePrediction',
                        messageTop: 'Report generated',
                        customize: function (xlsx) {
                            var sheet = xlsx.xl.worksheets['sheet1.xml'];
                        }
                    },
                    {
                        extend: 'pdfHtml5',
                        orientation: 'landscape',
                        pageSize: 'A0'
                    },
                    // 'print',
                    {
                        extend: 'colvis',
                        postfixButtons: ['colvisRestore']
                    },
                    {
                        extend: 'colvisGroup',
                        text: 'Show all',
                        show: ':hidden'
                    },
                    {
                        extend: 'colvisGroup',
                        text: 'Hide all',
                        hide: [':gt(0)'],
                    },

                ],
                rowCallback: function (row, data, index) {
                    if ($(row).text().indexOf("ERROR") !== -1) {
                        $(row).addClass("errorRow")
                    }
                }
            });


        $("#datatable-container").fadeIn('slow');

    }
    if (datatable != null) {
        updateHottie();
        $('.DTFC_Cloned .smileColumn').parent().popover({
            container: "body",
            placement: "right",
            trigger: "hover",
            content: function () {
                return $(this).text();
            }


        }).on('shown.bs.popover', function () {
            $('.popover').css({left: '-150px',});
        });

        // datatable.rows().every( function () {
        //     if ( $( table.cell( this.index(), 0 ).node() ).text() !== 'sometext' ) {
        //         $( table.cell( this.index(), 1 ).node() ).addClass( 'highlight' );
        //     }
        // } );


    }

    $(".loadingData").hide()

    $('.search_input').removeClass('disabled').removeAttr('disabled');
    $('.searchbar').removeClass('disabled');

    $('#calculate-one-smile-button').removeClass('disabled').removeAttr('disabled');
    datatable.draw();

}

function checkState(id) {
    var feedback = $.ajax({
        url: "/checkState",
        type: 'get',
        data: {id: id},
        headers: {
            'X-CSRF-Token': $('meta[name="csrf-token"]').attr('content')
        },
        async: false,
        success: function (response) {
            parseResponse(response, function () {


                $('.progress-bar')
                    .css('width', response.finished + '%')
                    .attr('aria-valuenow', response.finished)
                    .text(response.finished + '%');

                if (response.finished == 100) {
                    setTimeout(function () {
                        finaliceAnalysis(response)
                    }, 2000);
                } else {
                    if (response.finished < 50 && response.finished > 0) {

                    } else if (response.finished > 50) {
                        $('#calculating-molecular-descriptors').fadeOut("slow", function () {
                            $('#predicting-data').fadeIn('slow');
                        });
                    }
                    checkStateTimeout = setTimeout(function () {
                        checkState(id)
                    }, 2000);
                }


            }, function () {
                console.log("onerror")
                finaliceAnalysis(response)

            });
        },
        error: function (response) {
            parseResponse(response, null)
            clearTimeout(updateHottieTimeOut);

        }

    });

    $('div.feedback-box').html(feedback);
}


$(function () {

    // $(window).resize(function() {
    //     $('#container').height($(window).height() - 115);
    // });
    //
    // $(window).trigger('resize');


    if ($("#my-dropzone").length) {
        var dropzone = new Dropzone('#my-dropzone', {
            url: '/upload',
            clickable: '#drop-button',
            maxFilesize: 5,
            maxFiles: 1,
            previewsContainer: false,
            acceptedFiles: "text/plain,text/csv,text/tab-separated-values,.tsv",
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            init: function () {
                this.on('addedfile', function (file) {
                });
                this.on("maxfilesexceeded", function (file) {
                    this.removeAllFiles();
                    this.addFile(file);
                });

                this.on('dragenter', function () {
                    $(this).addClass("dragenter")
                });

                this.on('dragleave', function () {
                    $(this).removeClass("dragenter")

                });


                this.on('success', function (file, result) {

                    parseResponse(result, function () {
                        initializeCheckState(result.id)
                    }, function () {
                        finaliceAnalysis()
                    });

                });
            }
        });
    }


    var cont = 0;

    $("#calculate-one-smile-button").click(function () {
        if ($("#smileInput").val() == "") {
            cont++;
            if (cont > 10) {
                swal({
                    title: "Attention!",
                    text: "Please, insert one Smile!",
                    html: true,
                    type: "warning",
                });
                cont = 0;
            }
            return false;
        } else {
            $("#calculate-one-smile").submit();
        }
    });
    $("#calculate-one-smile").submit(function (e) {
        e.preventDefault();
        var postData = $(this).serialize();
        var url = $(this).attr("action");

        $.ajax({
            url: url,
            type: 'GET',
            data: postData,
            datatype: 'json',
            beforeSend: function () {
                $('#upload-container').hide();
                $('#predicting-data').fadeIn('slow');
                $('.search_input').addClass('disabled').attr('disabled', 'disabled');
                $('.searchbar').addClass('disabled');
                $('#calculate-one-smile-button').addClass('disabled').attr('disabled', 'disabled');
            },
            success: function (response) {
                parseResponse(response, function () {
                    setTimeout(function () {

                        finaliceAnalysis(response)
                    }, 2000);
                });
            },
            error: function (response) {
                parseResponse(response, function () {

                    setTimeout(function () {
                        $('#predicting-data').hide();
                        $('#upload-container').fadeIn('slow');
                    }, 2000);


                })
            }
        });
    });


    $(window).on('scroll', function () {
        if ($(window).scrollTop() > 10) {
            $('.navbar').addClass('active');
        } else {
            $('.navbar').removeClass('active');
        }
    });


    /*=========================================*/
    /*                tooltip                  */
    /*=========================================*/
//     $("body").tooltip({
//         selector: '[data-toggle="tooltip"]',
//         container: 'body',
//         trigger: "hover"
//     }).on('show.bs.tooltip', function () {
// // Only one tooltip should ever be open at a time
// //         $('.tooltip').not(this).hide();
//     });
//     $('.tooltip-active').tooltip('show');
//     var popOverSettings = {
//         container: 'body',
//         selector: '[data-toggle="popover"]', //Sepcify the selector here
//         html: true,
//         trigger: 'hover click',
//         content: function () {
//             $('.popover').popover('hide');
//             if ($(this).data("width")) {
//                 $(this).data("width")
//             }
//             if ($(this).data("html")) {
//                 return $(this).find(".content").html();
//             } else {
//                 return $(this).data("content");
//             }
//         },
//         title: function () {
//
// //                        console.log($(this).data("html"))
// //
// //            return $(this).html();
//         }
//     };
//     $('body').popover(popOverSettings).on("inserted.bs.popover", function (e) {
//
//         if ($(e.target).attr("data-max-width")) {
//             var w = $(e.target).attr("data-max-width");
//             $("div.popover").css({"max-width": w + "px"});
//         }
//     });


    /*=========================================*/
    /*             scroll to top               */
    /*=========================================*/
    $(window).scroll(function () {
        if ($(this).scrollTop() > 50) {
            $('#back-to-top').fadeIn();
        } else {
            $('#back-to-top').fadeOut();
        }
    });
    if ($(this).scrollTop() > 50) {
        $('#back-to-top').fadeIn();
    } else {
        $('#back-to-top').fadeOut();
    }

    $('#back-to-top').click(function () {
        $('#back-to-top').tooltip('hide');
        $('body,html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });


    /*=========================================*/
    /*             ajax form                   */
    /*=========================================*/
    $("#header,#content,#myModal").on("submit", "form.ajax", function (e) {
        e.preventDefault();
        var isModalForm = $(this).closest("#myModal").length > 0;
        var postData = $(this).serialize();
        var url = $(this).attr("action");
        var container = $(this).closest("div.form-ajax-container");
        var originalHtml = container.html();
        var loadingText = "Please wait...";
        var callBack = $(this).data("callback");
        if ($(this).data("loading-text")) {
            loadingText = $(this).data("loading-text");
        }
        container.empty();
        container.html("<div id='form-ajax-message'></div>");
        container = container.find("#form-ajax-message");
        container.html(loadingTemplate.format(loadingText));
        var successTemplate = $("#success-animation").html();
        var errorTemplate = $("#error-animation").html();
        $.ajax({
            url: url,
            type: 'POST',
            data: postData
        }).done(function (response) {
            if (response.success) {
                container.html(successTemplate.format(response.message));
            } else {
                container.html(errorTemplate.format(response.message));
            }
            if (callBack) {
                executeFunctionByName(callBack, window, response.success);
            }
            if (isModalForm) {
                setTimeout(function () {
                    $(this).closest("#myModal").modal('toggle');
                }, 5000);
            } else {
                setTimeout(function () {
                    container.html(originalHtml)
                }, 5000);
            }

        });
    });

    /*=========================================*/
    /*                required                 */
    /*=========================================*/
    // $('.required-icon').tooltip({
    //     placement: 'top',
    //     title: 'Required field',
    // });


    $('.toggle').click(function () {
        $input = $(this);
        $target = $('#' + $input.attr('data-toggle'));
        $target.slideToggle();
    });


});


function ajaxError(XMLHttpRequest, textStatus, errorThrown) {
    setTimeout(function () {
        swal({
            title: "Ops! one error ocurs",
            text: XMLHttpRequest.responseText,
            html: true,
            type: "warning",
        });
    }, 1000);
}


function parseResponse(response, onSuccess, onError) {
    response = response || false;
    var message = "Unknown error";
    var title = "Ops! one error ocurs";
    var type = "error";
    if (response) {
        if (response.success) {
            if (onSuccess != undefined) {
                onSuccess(response);
            }
            if (response.message != undefined) {
                message = response.message;
                type = "success";
                title = "Success!!";
            } else {
                return null;
            }
        } else {
            message = response.message;
        }
    }
    setTimeout(function () {
        swal({
            title: title,
            text: message,
            type: type,
        }, function () {
            if (response.success) {
                if (onSuccess != undefined) {
                    onSuccess(response);
                }
            }

            if (onError != undefined) {
                onError();
            }
        });
    }, 200);
}


function toObject(arr) {
    var rv = {};
    for (var i in arr)
        rv[i] = arr[i];
    return rv;
}

function round(value, roundPrecision) {
    value = parseFloat(value);
    if (roundPrecision != 0)
        roundPrecision = roundPrecision || 3;
    if (isNaN(value)) {
        return "(?)";
    } else {
        return value.toFixed(roundPrecision);
    }
}

function formatDate(date) {
    if (dateUTC == null) {
        var dateUTC = $("#dateUTC").text();
    }
    return date += " " + dateUTC;
}
