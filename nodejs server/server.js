var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');
const bcrypt = require('bcryptjs');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

app.get('/', function (req, res) {
    return res.send({ error: true, message: 'Test Moneyhub Web API' })
});

var dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'moneyhubandorid'
});

dbConn.connect();

app.post('/user', function (req, res) {

    var user = req.body

    if (!user) {
        return res.status(400).send({ error: true, message: 'Please provide user ' });
    }

    dbConn.query("INSERT INTO users SET ? ", user, function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

app.post('/login', async function (req, res) {
    let email = req.body.email;
    let password = req.body.password;

    if (!email || !password) {
        return res.status(400).send({ "success": 0, "message": 'Please provide student id and password.' });
    }

    dbConn.query('SELECT * FROM users WHERE email = ?', [email], function (error, results, fields) {
        if (error) throw error;
        if (results[0]) {
            bcrypt.compare(password, results[0].password, function (error, result) {
                if (error) throw error;
                if (result) {
                console.log(results[0])
                    return res.send({
                        "success": 1, "iduser": results[0].iduser ,"email": results[0].email, "firstname": results[0].firstname,
                        "lastname": results[0].lastname, "birthday": results[0].birthday, "profile_photo_path": results[0].profile_photo_path,
                        "idcareer": results[0].idcareer, "idgender": results[0].idgender
                    });
                } else {
                    return res.send({ "success": 0 });
                }
            });
        } else {
            return res.send({ "success": 0 });
        }
    });
});

app.post('/insertAccount', async function (req, res) {
    let post = req.body;
    let firstname = post.firstname;
    let lastname = post.lastname;
    let birthday = post.birthday;
    let email = post.email;
    let password = post.password;
    let idcareer = post.idcareer;
    let idgender = post.idgender;
    const salt = await bcrypt.genSalt(10);
    let password_hash = await bcrypt.hash(password, salt);

    if (!post) {
        return res.status(400).send({ error: true, message: 'Please provide a user data' });
    }

    dbConn.query('SELECT * FROM users WHERE email = ?', email, function (error, results, fields) {
        if (error) throw error;
        if (results[0]) {
            return res.status(400).send({ error: true, message: 'This email is already in database.' });
        } else {
            var insertData = "INSERT INTO users(firstname, lastname, birthday, email, password, idcareer, idgender) VALUES('"
                + firstname + "','" + lastname + "','" + birthday + "','" + email + "','" + password_hash + "','" + idcareer
                + "','" + idgender + "')"

            dbConn.query(insertData, (error, results) => {
                if (error) throw error;
                return res.send(results);
            });
        }
    });
});

app.get('/getCareer', async (req, res)=> {
    dbConn.query('SELECT * FROM careers', null, (error, results, fields)=>{
        try {
            if(error) throw error
            if(results) {
                return res.status(400).send({
                    success : 1,
                    results : results
                })
            }
        } catch (error) {
            return res.status(400).send({
                success : 0,
                results: error
            })
        }
    })
})

app.get('/getGender', async (req, res)=> {
    dbConn.query('SELECT * FROM genders', null, (error, results, fields)=>{
        try {
            if(error) throw error
            if(results) {
                return res.status(400).send({
                    success : 1,
                    results : results
                })
            }
        } catch (error) {
            return res.status(400).send({
                success : 0,
                results: error
            })
        }
    })
})


//set port
app.listen(3000, function () {
    console.log('Node app is running on port 3000');
});

module.exports = app;