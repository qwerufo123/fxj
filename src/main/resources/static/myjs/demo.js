var app = new Vue({
    el: '#app',
    data: {
    },
    mounted() {
	axios.get('http://localhost:8080', {
              params: {
              }
        })
        .then(function () {
        })
        .catch(function (error) {
            console.log(error)
        });
    },
    computed: {
        
    },
    methods: {
    }
})