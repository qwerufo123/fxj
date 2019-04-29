var head = new Vue({
    el: '#head',
    data: {
        input: '',


    },
    mounted() {

    },
    computed: {
        
    },
    methods: {
        show() {
            var heightReg = new RegExp("^[0-9]+$")
            var blockReg = new RegExp("^0{10}[a-zA-Z0-9]{54}$")
            var merkleReg = new RegExp("^[a-zA-Z0-9]{64}$")
            if(this.input==''){
                $("#temp").text("查询内容为空")
                return
            }else if(this.input.length<10 && heightReg.test(this.input)){
                location.href="http://localhost:8080/block.html?blockheight="+this.input
            }else if(this.input.length==64 && blockReg.test(this.input)){
                location.href="http://localhost:8080/block.html?blockhash="+this.input
            }else if(this.input.length==64 && merkleReg.test(this.input)){
                location.href="http://localhost:8080/tx.html?txhash="+this.input
            }else{
                $("#temp").text("输入内容有误")
            }
        }
    }
})
var body = new Vue({
    el: '#body',
    data: {
        tableData: [],
        sign: 'block',

    },
    mounted() {
        this.getIndexData();
        // this.webSocket();
    },
    methods: {
        getIndexData() {
            axios.get('http://localhost:8080/html/index', {
                params: {
                    sign: this.sign,
                }
            })
                .then(function (data) {
                    if(body.sign=="block"){
                        body.toBlock(data.data)
                    }else if(body.sign=="tx"){
                        body.toTx(data.data)
                    }else{
                        console.log("sign is error")
                    }
                })
                .catch(function (error) {
                    console.log(error)
                });
        },
        toBlock(data){
            for(let i = 0 ; i < data.length ; i ++){
                // data[i].time = new Date(parseInt(data[i].time)*1000)
                data[i].time = new Date(parseInt(data[i].time)*1000).toString();
            }
            this.tableData = data
        },
        toTx(data){
            for(let i = 0 ; i < data.length ; i ++){
                // data[i].time = new Date(parseInt(data[i].time)*1000)
                data[i].time = new Date(parseInt(data[i].time)*1000).toString();
            }
            this.tableData = data
        },
        block() {
            this.sign = "block"
            this.getIndexData()
            $("#block").css("background-color", "red")
            $("#tx").css("background-color", "#409EFF")
            $("#blockTable").css("display", "inline-block")
            $("#txTable").css("display", "none")
        },
        tx() {
            this.sign = "tx"
            this.getIndexData()
            $("#tx").css("background-color", "red")
            $("#block").css("background-color", "#409EFF")
            $("#txTable").css("display", "inline-block")
            $("#blockTable").css("display", "none")
        },
        height(height){
            location.href="http://localhost:8080/block.html?blockheight="+height
        },
        merkleRoot(merkleRoot){
            location.href="http://localhost:8080/merkle.html?merkleRoot="+merkleRoot
        },
        txhash(txhash){
            location.href="http://localhost:8080/tx.html?txhash="+txhash
        },
        webSocket(){
            ws = new WebSocket('ws://localhost:8080/websocket');
            
            ws.onmessage = function(data){
                console.log(data.data)
            }
        },
        aaa(){
            ws.send("block");
        },
        bbb(){
            ws.send("tx");
        }
    }
})