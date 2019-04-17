//load our app server using express
const express = require('express')
var cors = require('cors')
const app = express()
const morgan = require('morgan')

const bodyParser = require('body-parser')

app.use(cors())

app.use(bodyParser.urlencoded({ extended: false }))

//important.. this line creates a connection to use static files such as html saved in the
//folder public

app.use(express.static('./uploads'));

app.use(morgan('short'))

global.__basedir = __dirname;


//create router
//this is important because it creates a connection to the users folder and runs the users.js folder
const router = require('./routes/users.js')
app.use(router)


//localhost:3003
app.get('/', function(req, res) {
    res.send('SAVVY API');
});


app.listen(8080, '172.31.90.190');
