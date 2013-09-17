'use strict';

window.App = Ember.Application.create({
    LOG_TRANSITIONS: true
});

App.IndexRoute = Ember.Route.extend({
    model: function() {
        return this.store.find('example');
    }
});

App.Example = DS.Model.extend({
    name: DS.attr()
});

