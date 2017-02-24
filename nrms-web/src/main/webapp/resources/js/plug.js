(function(w, d, $) {
    require(["swiper"], function() {
        $(document).on("click", ".plug1_swiper .swiper-button-prev,.plug1_swiper .swiper-button-next", function(e) {
            e.stopPropagation();
        })
        var plug1_swiper = new Swiper('.plug1_swiper', {
            slidesPerView: 4,
            autoplay: 2000,
            onSlideChangeStart: function(swiper) {
                if (plug1_swiper.activeIndex == 0) {
                    $(".plug1_swiper .swiper-button-prev").css("background", "rgba(0, 0, 0, .1)");
                    $(".plug1_swiper .swiper-button-next").css('background', '');;
                } else if ((plug1_swiper.activeIndex) == ($(".plug1_swiper .swiper-slide").length - 4)) {
                    $(".plug1_swiper .swiper-button-next").css("background", "rgba(0, 0, 0, .1)");
                    $(".plug1_swiper .swiper-button-prev").css('background', '');;
                } else {
                    $(".plug1_swiper .swiper-button-prev").css("background", "");
                    $(".plug1_swiper .swiper-button-next").css('background', '');
                }
            }
        });

        $(document).on("click", ".plug1_swiper .swiper-button-prev", function(e) {
            e.stopPropagation();
            plug1_swiper.swipePrev();
        });
        $(document).on("click", ".plug1_swiper .swiper-button-next", function(e) {
            e.stopPropagation();
            plug1_swiper.swipeNext();
        });

        $(document).on("mouseenter", ".plug1_swiper", function() {
            plug1_swiper.stopAutoplay();
            if (plug1_swiper.activeIndex == 0) {
                $(".plug1_swiper .swiper-button-prev").css("background", "rgba(0, 0, 0, .1)");
            }
            $(".plug1_swiper .swiper-button-prev,.plug1_swiper .swiper-button-next").stop().fadeIn();
        });
        $(document).on("mouseleave", ".plug1_swiper", function() {
            plug1_swiper.startAutoplay();
            $(".plug1_swiper .swiper-button-prev,.plug1_swiper .swiper-button-next").stop().fadeOut();

        });


        $(document).on("click", ".plug1_tag_btn .close_btn", function(e) {
            e.stopPropagation();
            $("#modal_panel_colse").modal('show');
            var _this = this;
            $("#modal_panel_colse .on").off();
            $("#modal_panel_colse .on").on("click", function() {
                $(_this).parent().parent().parent().remove();
            });
            return false;
        });
    });
})(window, document, window.jQuery);



(function(w, d, $) {
    require(
        [
            'zrender',
            'zrender/shape/Circle',
            'zrender/shape/BezierCurve'
        ],
        function(zrender) {
            var zr = zrender.init(document.getElementById('plug2_main'));
            zr.clear();
            var color = require('zrender/tool/color');
            var curve = require('zrender/tool/curve');
            var colorIdx = 0;
            var width = Math.ceil(zr.getWidth());
            var height = Math.ceil(zr.getHeight());
            var atimer = [];

            var CircleShape = require('zrender/shape/Circle');
            var BezierCurveShape = require('zrender/shape/BezierCurve');

            var data = [{
                "id": 1,
                "type": 1,
                "name": "d1",
                "color": "#2cc9f6",
                "line": [{
                    "type": 1,
                    "id": 14,
                    "color": "#27d37d"
                }, {
                    "type": 5,
                    "id": 3,
                    "color": "#959595"
                }]
            }, {
                "id": 2,
                "type": 1,
                "name": "d2",
                "color": "#2cc9f6",
                "line": [{
                    "type": 2,
                    "id": 13,
                    "color": "#fe4526"
                }, {
                    "type": 1,
                    "id": 9,
                    "color": "#27d37d"
                }]
            }, {
                "id": 3,
                "type": 1,
                "name": "d3",
                "color": "#2cc9f6",
                "line": [{
                    "type": 5,
                    "id": 4,
                    "color": "#959595"
                }, {
                    "type": 2,
                    "id": 112,
                    "color": "#fe4526"
                }, {
                    "type": 1,
                    "id": 8,
                    "color": "#27d37d"
                }]
            }, {
                "id": 4,
                "type": 1,
                "name": "d4",
                "color": "#2cc9f6",
                "line": [{
                    "type": 1,
                    "id": 13,
                    "color": "#27d37d"
                }, {
                    "type": 2,
                    "id": 7,
                    "color": "#fe4526"
                }]
            }, {
                "id": 5,
                "type": 2,
                "name": "d5",
                "color": "#26d078",
                "line": [{
                    "type": 3,
                    "id": 10,
                    "color": "#ffbe26"
                }]
            }, {
                "id": 6,
                "type": 2,
                "name": "d6",
                "color": "#26d078",
                "line": [{
                    "type": 3,
                    "id": 10,
                    "color": "#ffbe26"
                }]
            }, {
                "id": 7,
                "type": 2,
                "name": "d7",
                "color": "#26d078",
                "line": [{
                    "type": 2,
                    "id": 12,
                    "color": "#fe4526"
                }]
            }, {
                "id": 8,
                "type": 2,
                "name": "d8",
                "color": "#26d078",
                "line": [{
                    "type": 3,
                    "id": 1,
                    "color": "#ffbe26"
                }]
            }, {
                "id": 9,
                "type": 2,
                "name": "d9",
                "color": "#26d078",
                "line": [{
                    "type": 4,
                    "id": 16,
                    "color": "#27cf7a"
                }]
            }, {
                "id": 10,
                "type": 2,
                "name": "d10",
                "color": "#26d078",
                "line": [{
                    "type": 4,
                    "id": 14,
                    "color": "#27cf7a"
                }]
            }, {
                "id": 11,
                "type": 3,
                "name": "d11",
                "color": "#ff9326",
                "line": [{
                    "type": 5,
                    "id": 13,
                    "color": "#959595"
                }]
            }, {
                "id": 12,
                "type": 3,
                "name": "d12",
                "color": "#ff9326",
                "line": [{
                    "type": 4,
                    "id": 1,
                    "color": "#27cf7a"
                }]
            }, {
                "id": 13,
                "type": 3,
                "name": "d13",
                "color": "#ff9326",
                "line": [{
                    "type": 4,
                    "id": 1,
                    "color": "#27cf7a"
                }]
            }, {
                "id": 14,
                "type": 3,
                "name": "d14",
                "color": "#ff9326",
                "line": [{
                    "type": 5,
                    "id": 2,
                    "color": "#959595"
                }]
            }, {
                "id": 15,
                "type": 3,
                "name": "d15",
                "color": "#ff9326",
                "line": [{
                    "type": 4,
                    "id": 3,
                    "color": "#27cf7a"
                }, {
                    "type": 3,
                    "id": 5,
                    "color": "#ffbe26"
                }]
            }, {
                "id": 16,
                "type": 3,
                "name": "d16",
                "color": "#ff9326",
                "line": [{
                    "type": 3,
                    "id": 3,
                    "color": "#ffbe26"
                }, {
                    "type": 4,
                    "id": 4,
                    "color": "#27cf7a"
                }]
            }, {
                "id": 17,
                "type": 3,
                "name": "d17",
                "color": "#ff9326",
                "line": []
            }];


            var length = data.length;
            var R = width / 2 - 50;
            var r = 20;
            var angle = 360 / length;
            var dx = width / 2;
            var dy = height / 2;


            var coordinateFn = {
                x: function(dx, dy, Rr, angle) {
                    var hudu = (2 * Math.PI / 360) * angle;
                    return dx + Math.sin(hudu) * Rr;
                },
                y: function(dx, dy, Rr, angle) {
                    var hudu = (2 * Math.PI / 360) * angle;
                    return dy - Math.cos(hudu) * Rr;
                }
            }

            //圆点
            function dotFn(x1, y1, x2, y2, color) {
                function circle_creat(x1, y1, x2, y2, color, delay) {
                    var circle = new CircleShape({
                        id: require('zrender/tool/guid')(),
                        zlevel: 2,
                        position: [curve.quadraticAt(x1, dx, x2, 0), curve.quadraticAt(y1, dy, y2, 0)],
                        style: {
                            x: 0,
                            y: 0,
                            r: 2,
                            brushType: 'both',
                            color: '#fff',
                            strokeColor: color,
                            lineWidth: .5,
                            shadowBlur: 5,
                            opacity: .9,
                            shadowColor: color,
                            text: '',
                            textPosition: 'inside'
                        },
                        draggable: false
                    });
                    zr.addShape(circle);

                    zr.animate(circle.id, "", true).delay(delay)
                        .when(250, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.05), curve.quadraticAt(y1, dy, y2, 0.05)]
                        })
                        .when(500, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.1), curve.quadraticAt(y1, dy, y2, 0.1)]
                        })
                        .when(750, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.15), curve.quadraticAt(y1, dy, y2, 0.15)]
                        })
                        .when(1000, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.2), curve.quadraticAt(y1, dy, y2, 0.2)]
                        })
                        .when(1250, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.25), curve.quadraticAt(y1, dy, y2, 0.25)]
                        })
                        .when(1500, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.3), curve.quadraticAt(y1, dy, y2, 0.3)]
                        })
                        .when(1750, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.35), curve.quadraticAt(y1, dy, y2, 0.35)]
                        })
                        .when(2000, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.4), curve.quadraticAt(y1, dy, y2, 0.4)]
                        })
                        .when(2250, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.45), curve.quadraticAt(y1, dy, y2, 0.45)]
                        })
                        .when(2500, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.5), curve.quadraticAt(y1, dy, y2, 0.5)]
                        }).when(2750, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.55), curve.quadraticAt(y1, dy, y2, 0.55)]
                        })
                        .when(3000, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.6), curve.quadraticAt(y1, dy, y2, 0.6)]
                        })
                        .when(3250, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.65), curve.quadraticAt(y1, dy, y2, 0.65)]
                        })
                        .when(3500, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.7), curve.quadraticAt(y1, dy, y2, 0.7)]
                        })
                        .when(3750, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.75), curve.quadraticAt(y1, dy, y2, 0.75)]
                        })
                        .when(4000, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.8), curve.quadraticAt(y1, dy, y2, 0.8)]
                        })
                        .when(4250, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.85), curve.quadraticAt(y1, dy, y2, 0.85)]
                        })
                        .when(4500, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.9), curve.quadraticAt(y1, dy, y2, 0.9)]
                        })
                        .when(4750, {
                            position: [curve.quadraticAt(x1, dx, x2, 0.95), curve.quadraticAt(y1, dy, y2, 0.95)]
                        })
                        .when(5000, {
                            position: [curve.quadraticAt(x1, dx, x2, 1), curve.quadraticAt(y1, dy, y2, 1)]
                        }).start();
                }
                var time = Math.round(Math.random() * 2500);
                var timer1 = setTimeout(function() {
                    circle_creat(x1, y1, x2, y2, color);
                    var timer2 = setTimeout(function() {
                        circle_creat(x1, y1, x2, y2, color);
                    }, 2500);
                    atimer.push(timer2);
                }, time);
                atimer.push(timer1);
            }




            //线
            function lineFn(ind, circletype, linetype) {
                for (var i = 0; i < data[ind].line.length; i++) {
                    if (data[ind].line[i].type != linetype && linetype != "") {
                        continue;
                    }
                    for (var j = 0; j < data.length; j++) {
                        if (data[j].type != circletype && circletype != "") {
                            continue;
                        }
                        if (data[j].id == data[ind].line[i].id) {
                            var x1 = coordinateFn.x(dx, dy, R, angle * (ind + 1))
                            var x2 = coordinateFn.x(dx, dy, R, angle * (j + 1));
                            var y1 = coordinateFn.y(dx, dy, R, angle * (ind + 1));
                            var y2 = coordinateFn.y(dx, dy, R, angle * (j + 1));
                            var color = data[ind].line[i].color;


                            var line = new BezierCurveShape({
                                zlevel: 1,
                                style: {
                                    xStart: x1,
                                    yStart: y1,
                                    cpX1: dx,
                                    cpY1: dy,
                                    xEnd: x2,
                                    yEnd: y2,
                                    strokeColor: color,
                                    lineWidth: 1,
                                    text: ''
                                },
                                draggable: false,
                                clickable: true
                            });

                            line.to_id = data[j].id;

                            zr.addShape(line);

                            line.onclick = function(event) {
                                lineMenu(data[ind].id, this.to_id, event);
                                return false;
                            }
                            dotFn(x1, y1, x2, y2, color);
                        }
                    }
                }
            }

            //点击线
            function lineMenu(frome_id, to_id, event) {
                $(".plug2_line_menu li:eq(1)").html("id" + frome_id + "到id" + to_id)
                $(".plug2_line_menu").css({
                    'top': event.event.clientY - $(".plug2_zrender_box").offset().top + 20,
                    'left': event.event.clientX - $(".plug2_zrender_box").offset().left - 90,
                    'display': 'block'
                });

            }

            //圆
            function init(circletype, linetype) {
                zr.modLayer(2, {
                    'motionBlur': false
                });
                for (n in atimer) {
                    clearTimeout(atimer[n]);
                }
                zr.clear();
                atimer = [];
                zr.modLayer(2, {
                    'motionBlur': true,
                    'lastFrameAlpha': .9
                });
                for (var i = 0; i < data.length; i++) {
                    zr.addShape(new CircleShape({
                        zlevel: 3,
                        style: {
                            x: coordinateFn.x(dx, dy, R, angle * (i + 1)),
                            y: coordinateFn.y(dx, dy, R, angle * (i + 1)),
                            r: r,
                            brushType: 'both',
                            color: data[i].color,
                            strokeColor: data[i].color,
                            lineWidth: 0,
                            text: data[i].name,
                            textFont: '14px Microsoft YaHei',
                            textPosition: 'inside'
                        },
                        hoverable: false,
                        draggable: false,
                        clickable: true,

                    }));
                    lineFn(i, circletype, linetype)
                }
                zr.render();

            }
            init("", "");
            $("#plug2_host,#plug2_line").change(function() {
                init($("#plug2_host").val(), $("#plug2_line").val());
            });
        }
    );
})(window, document, window.jQuery);



(function(w, d, $) {
    require(["ucharts"], function() {
        var labelTop = {
            normal: {
                label: {
                    show: true,
                    position: 'center',
                    formatter: '{b}',
                    textStyle: {
                        baseline: 'bottom',
                        color: '#666'
                    }
                },
                labelLine: {
                    show: false
                }
            }
        };
        var labelFromatter = {
            normal: {
                label: {
                    formatter: function(params) {
                        return 100 - params.value + '%'
                    },
                    textStyle: {
                        baseline: 'top',
                        color: '#666'
                    }
                }
            },
        }
        var labelBottom = {
            normal: {
                color: '#ccc',
                label: {
                    show: true,
                    position: 'center'
                },
                labelLine: {
                    show: false
                }
            },
            emphasis: {
                color: 'rgba(0,0,0,0)'
            }
        };
        var radius = [60, 65];
        var option = {
            color: ["#09c"],
            legend: {
                show: false,
                x: 'center',
                y: 'bottom',
                data: [
                    '广州', '广西', '重庆', '北广', '至上', '形军'
                ]
            },
            title: {
                text: '全国绿化率',
                subtext: '来自广州绿化局',
                x: 'center'
            },
            toolbox: {
                show: false
            },
            series: [{
                type: 'pie',
                center: ['16%', '30%'],
                radius: radius,
                itemStyle: labelFromatter,
                data: [{
                    name: 'other',
                    value: 46,
                    itemStyle: labelBottom
                }, {
                    name: '广州',
                    value: 54,
                    itemStyle: labelTop
                }]
            }, {
                type: 'pie',
                center: ['50%', '30%'],
                radius: radius,
                itemStyle: labelFromatter,
                data: [{
                    name: 'other',
                    value: 56,
                    itemStyle: labelBottom
                }, {
                    name: '广西',
                    value: 44,
                    itemStyle: labelTop
                }]
            }, {
                type: 'pie',
                center: ['84%', '30%'],
                radius: radius,
                itemStyle: labelFromatter,
                data: [{
                    name: 'other',
                    value: 65,
                    itemStyle: labelBottom
                }, {
                    name: '重庆',
                    value: 35,
                    itemStyle: labelTop
                }]
            }, {
                type: 'pie',
                center: ['16%', '70%'],
                radius: radius,
                itemStyle: labelFromatter,
                data: [{
                    name: 'other',
                    value: 78,
                    itemStyle: labelBottom
                }, {
                    name: '形军',
                    value: 22,
                    itemStyle: labelTop
                }]
            }, {
                type: 'pie',
                center: ['50%', '70%'],
                radius: radius,
                itemStyle: labelFromatter,
                data: [{
                    name: 'other',
                    value: 78,
                    itemStyle: labelBottom
                }, {
                    name: '北广',
                    value: 22,
                    itemStyle: labelTop
                }]
            }, {
                type: 'pie',
                center: ['84%', '70%'],
                radius: radius,
                itemStyle: labelFromatter,
                data: [{
                    name: 'other',
                    value: 78,
                    itemStyle: labelBottom
                }, {
                    name: '至上',
                    value: 22,
                    itemStyle: labelTop
                }]
            }]
        };
        var aplug5_chart = document.getElementsByClassName('plug5_chart');
        for (var i = 0; i < aplug5_chart.length; i++) {
            var myChart = ucharts.init(aplug5_chart[i]);
            myChart.setOption(option);
        }




    })
    require(["swiper"], function() {
        var plug5_swiper = new Swiper('.plug5_swiper', {
            autoplay: 2000,
            onSlideChangeStart: function(swiper) {
                if (plug5_swiper.activeIndex == 0) {
                    $(".plug5_box .swiper-button-prev").css("background", "rgba(0, 0, 0, .1)");
                    $(".plug5_box .swiper-button-next").css('background', '');;
                } else if ((plug5_swiper.activeIndex) == ($(".plug5_box .swiper-slide").length - 4)) {
                    $(".plug5_box .swiper-button-next").css("background", "rgba(0, 0, 0, .1)");
                    $(".plug5_box .swiper-button-prev").css('background', '');;
                } else {
                    $(".plug5_box .swiper-button-prev").css("background", "");
                    $(".plug5_box .swiper-button-next").css('background', '');
                }
            }
        });

        $(document).on("click", ".plug5_box .swiper-button-prev", function(e) {
            e.stopPropagation();
            plug5_swiper.swipePrev();
        });
        $(document).on("click", ".plug5_box .swiper-button-next", function(e) {
            e.stopPropagation();
            plug5_swiper.swipeNext();
        });

        $(document).on("mouseenter", ".plug5_box", function() {
            plug5_swiper.stopAutoplay();
            if (plug5_swiper.activeIndex == 0) {
                $(".plug5_box .swiper-button-prev").css("background", "rgba(0, 0, 0, .1)");
            }
            $(".plug5_box .swiper-button-prev,.plug5_box .swiper-button-next").stop().fadeIn();
        });
        $(document).on("mouseleave", ".plug5_box", function() {
            plug5_swiper.startAutoplay();
            $(".plug5_box .swiper-button-prev,.plug5_box .swiper-button-next").stop().fadeOut();

        });
    });
})(window, document, window.jQuery);
