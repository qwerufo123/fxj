var app = new Vue({
    el: '#app',
    data: {
        merkle:''
    },
    mounted() {
	// axios.get('http://localhost:8080', {
    //           params: {
    //           }
    //     })
    //     .then(function () {
    //     })
    //     .catch(function () {
    //     });
        this.url()
    },
    methods: {
        url(){
            var url = new URL(location.href)
            this.merkle = url.searchParams.get("merkleRoot")
        }
    }
})