var app = new Vue({
    el: '#app',
    data: {
        txhash: '',
        tx:'',
    },
    mounted() {
        this.url();
        this.getTx();
        setTimeout(() => {
            this.setSumBit
        }, 300);
    },
    computed: {
        setSumBit(){
            var a = 0
            if(this.tx.vout!=undefined){
                for (let i = 0; i < this.tx.vout.length; i++) {
                    a = eval(this.tx.vout[i].value+'+'+a)
                }
            }
            return a
        }
    },
    methods: {
        url() {
            var url = new URL(location.href)
            this.txhash = url.searchParams.get("txhash")
        },
        getTx() {
            axios.get('http://localhost:8080/html/getTxByHash', {
                params: {
                    txhash:this.txhash
                }
            })
                .then(function (data) {
                    app.tx = data.data
                })
                .catch(function (error) {
                    console.log(error)
                });
        },
        txHash(){
            location.reload()
        },
        toVin(vinTxId){
            console.log(vinTxId)
        },
        toVout(voutTxId){
            console.log(voutTxId)
        },
       
    }

})