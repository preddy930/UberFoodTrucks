Backbone.View.prototype.close = function () {
    console.log('Closing view ' + this);
    if (this.beforeClose) {
        this.beforeClose();
    }
    this.remove();
    this.unbind();
};

var AppRouter = Backbone.Router.extend({

    initialize:function () {
    },

    routes:{
        "":"map"
    }
  });

var mapview = new App.MapView();

var router = new AppRouter();
router.on('route:map', function() {
 mapview.render();
});

Backbone.history.start();