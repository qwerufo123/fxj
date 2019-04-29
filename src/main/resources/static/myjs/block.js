var app = new Vue({
    el: '#app',
    data: {
        block: '',
        tx: [],
        blockheight: '',
        blockhash: '',
        maxheight: '',
        minheight: 0,
    },
    mounted() {
        this.URL()
        if (this.blockheight != null) {
            this.getBlockByHeight()
        } else if (this.blockhash != null) {
            this.getBlockByHash()
        }
    },
    methods: {
        URL() {
            var url = new URL(location.href)
            this.blockheight = url.searchParams.get("blockheight")
            this.blockhash = url.searchParams.get("blockhash")
        },
        getBlockByHeight() {
            axios.get('http://localhost:8080/html/getBlockByHeight', {
                params: {
                    blockheight: this.blockheight
                }
            })
                .then(function (data) {
                    app.dataDispose(data)
                })
                .catch(function (error) {
                    console.log(error)
                });
        },
        getBlockByHash() {
            axios.get('http://localhost:8080/html/getBlockByHash', {
                params: {
                    blockhash: this.blockhash
                }
            })
                .then(function (data) {
                    app.dataDispose(data)
                })
                .catch(function (error) {
                    console.log(error)
                });
        },
        dataDispose(data) {
            this.block = data.data
            this.tx = app.block.tx
            this.maxheight = data.data.maxheight
            this.block.miner = this.block.miner == undefined ? "Unknown" : this.block.miner
            this.block.time = new Date((parseInt(this.block.time) * 1000)).toString()
            // for (let i = 0; i < this.tx.length; i++) {
            //     if(this.tx[i].locktime==0){
            //         this.tx[i].locktime = undefined
            //         continue
            //     }
            //     this.tx[i].locktime = new Date(parseInt(this.tx[i].locktime)*1000).toString()
            // }
        },
        toLastBlock() {
            this.blockheight = eval(this.blockheight - 1)
            console.log(this.blockheight)
            location.href = "http://localhost:8080/block.html?blockheight=" + this.blockheight
        },
        toNextBlock() {
            this.blockheight = eval(parseInt(this.blockheight) + 1)
            console.log(this.blockheight)
            location.href = "http://localhost:8080/block.html?blockheight=" + this.blockheight
        },
        txhash(txhash){
            location.href="http://localhost:8080/tx.html?txhash="+txhash
        }
    }
})