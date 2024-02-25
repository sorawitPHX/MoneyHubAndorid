// var express = require('express');
// var app = express();
// var bodyParser = require('body-parser');
// var mysql = require('mysql');
// const bcrypt = require('bcrypt')

// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({
//     extended: true
// }));

// app.get('/', function(req,res){
//     return res.send({error:true, message:'Test Student Web API'})
// });

// var dbConn = mysql.createConnection({
//     host: 'localhost',
//     user:'root' ,
//     password:'',
//     database:'lad_connect_mysql'
// });

// dbConn.connect();

// app.get('/allStd', function(req,res){
//     dbConn.query('SELECT * FROM student', function(error,results, fields){
//         if (error) throw error;
//         return res.send(results);
//     });
// });

// app.post("/std", function (req, res){

//     var std = req.body

//     if(!std){
//         return res.status(400).send({ error:true, message: 'Pleasse provide student '});
//     }

//     dbConn.query("INSERT INTO student SET ? ", std , function(error, results, fields){
//         if (error) throw error;
//         return res.send(results);
//     });
// });

// //Lab09การค้นหาตามรหัสนักศึกษา
// app.get('/std/:id',function(req,res){
//     let std_id =req.params.id;
//     if(!std_id){
//         return res.status(400).send({ error: true, message: 'Please provide student id'});
//     }
//     dbConn.query('SELECT * FROM student WHERE std_id = ?', std_id, function (error,results,fields){
//         if (error) throw error;
//         if (results[0]){
//             return res.send(results[0]);
//         } else{
//             return res.status(400).send({ error: true,message: 'Student id Not Found!!'});
//         }
//     });
// })

// //การ Update ข้อมูลนักศึกษา
// app.put('/std/:id',function(req,res){
//     let std_id = req.params.id;
//     let std = req.body
//     if(!std_id || !std){
//         return res.status(400).send({ error: true, message: 'Please provide student id and student data'});
//     }

//     dbConn.query('UPDATE student SET ? WHERE std_id = ?', [std, std_id], function(error, results, fields){
//         if (error) throw error;

//         return res.send({ error: false, message: 'Student has been updeted seccessfully'});
//     });
// })

// //การลบข้อมูลนักศึกษา
// app.delete('/std/:id', function(req,res){
//     let std_id = req.params.id;
//     let std = req.body
//     if (!std_id || !std){
//         return res.status(400).send({ error: true, message: 'Please provide student id'});
//     }

//     dbConn.query('DELETE FROM student WHERE std_id = ?',[std_id], function(error, results, fields){
//         if (error) throw error;

//         return res.send({ error: false, message: 'Student has been deleted seccessfully'});
    
//     });
// })
// //การดึงข้อมูลทั้งหมดจากตาราง register_student
// app.get('/allResgiter', function(req,res){
//     dbConn.query('SELECT * FROM register_student', function(error,results, fields){
//         if(error) throw error;
//         return res.send(results);
//     });
// });

// //การ Login โดยมกีารตรวจสอบ std_id และ std_password ที่ตรงกับฐานข้อมูล
// app.get('/login/:std_id/:std_password', async function(req,res) {
//     let std_id = req.params.std_id;
//     let password = req.params.std_password;
//     if(!std_id || ! password ) { 
//         return res.status(400).send({ error: user, message: 'Please provide student id and password.'});

//     }
//     dbConn.query('SELECT * FROM register_student WHERE std_id = ? ', [std_id],
//                             function(error, results, fields){
//     if(error) throw error;
//     if (results[0]){
//         bcrypt.compare(password, results[0].std_password, function(error, results){
//             if(error) throw error;
//             if (result)
//             {return res.send({"success":1, "std_id": results[0].std_id ,"std_name":results[0].std_name,
//             "std_gender":results[0].std_gender,"role":results[0].role})}
//             else{return res.send({"success":0})}
//         });

//     }else{
//         return res.send({"success":0})}
//     });
// });

// //การดึงข้อมูลนักศึกษาจากรหัสนักศึกษา
// app.get('/search/:id', function (req, res){
//     let std_id = req.params.id;
//     if (!std_id){
//         return res.status(400).send({ error: true, message: 'Please provide student id'});
//     }
//     dbConn.query('SELECT * FROM register_student WHERE std_id = ?', std_id, function (error, result, fields){
//         if (error) throw error;
//         if (results[0]) {
//             return res.send({"std_id": results[0].std_id , "std_name":results[0].std_name,
//             "std_gender":results[0].std_gender,"role":results[0].role});
//         } else {
//             return res.status(400).send({ error: true, message: 'Student id Not Found!!'})
//         }
//     });
// });
// //การเพิ่มข้อมูลนักศึกษาและมกีารเข้ารหัส Password
// app.post('/insertAccount', async function (req, res){
//     let post = req.body
//     let std_id = post.std_id
//     let std_name = post.std_name
//     let std_password = post.std_password
//     let std_gender = post.std_gender
//     let role = post.role
//     const salt = await bcrypt.genSalt(10)
//     let password_hash = await bcrypt.hash(std_password,salt)

//     if (!post){
//         return res.status(400).send({ error:true, message: 'Please provide a student data'});
//     }

//     dbConn.query('SELECT * FROM register_student WHERE std_id = ?', std_id, function (error, results, fields) {
//         if (error) throw error;
//         if (results[0]) {
//             return res.status(400).send({ error: true, message: 'This student id is already in database.' });
//     } else {
//         if (!role) {
//             var insertData = "INSERT INTO register_student(std_id,std_name, std_password, std_gender) VALUES('"
//         + std_id + "','" + std_name + "','" + password_hash + "','" + std_gender +"')"
//     }else{
//         var insertData = "INSERT INTO register_student(std_id,std_name,std_password,std_gender,role)"
//         +" VALUES('"+ std_id + "','" + std_name  +"','"+ password_hash + "','" + std_gender +"', 'admin')"
//     }
//     dbConn.query(insertData, (error, results) => {
//                 if (error) throw error;
//                 return res.send(results);
//             });
//         }
//     });
// });
// //set port
// app.listen(3000, function(){
//     console.log('Node app is running on port 3000');
// });

// module.exports = app;
var express = require('express')
var app = express()
var bodyParser = require('body-parser')
var mysql = require('mysql')
var bcrypt = require('bcryptjs')

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
    extended: true
}))

app.get('/', function(req, res) {
    return res.send({error: false, message: 'Test Student Wen API'})
})

var dbCon = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'lab_connect_mysql'
})

dbCon.connect()

app.get('/allStd', (req, res) => {
    dbCon.query('SELECT * FROM student', (error, results, fields) => {
        if(error) throw error
        return res.send(results)
    })
})

app.post('/std', (req, res) => {
    var std = req.body
    if(!Object.keys(std).length) {
        return res.status(400).send({error: true, message: 'Please provide student'})
    }
    try {
        dbCon.query('INSERT into student SET ?', std, (error, results, fields) => {
            // if (error) throw new Error("Provide correct SQL Command")
            return res.send(results)
        })
    } catch (error) {
        return res.status(400).send({error:true, message: 'Please provide student'})
    }
        
})

app.get('/std/:id', (req, res)=>{
    let std_id = req.params.id
    try {
        dbCon.query('SELECT * FROM student WHERE std_id = ?', std_id, (error, results, fields)=>{
        if(results[0]) {
            return res.send(results[0])
        }else {
            return res.send({
                success : true,
                message: "nothing has been search"
            })
        }
    })
    } catch (error) {
        return res.status(400).send(error)
    }
})

app.put('/std/:id', (req, res)=> {
    let std_id = req.params.id
    let std = req.body

    try {
        dbCon.query('UPDATE student SET ? WHERE std_id = ?', [std, std_id], (error, results, fields)=>{ 
            if(results["affectedRows"]) {
                return res.send({
                    success : true,
                    message: "Student has been updated successfully"
                })
            }else {
                return res.send({
                    success : true,
                    message: "nothing has been deleted"
                })
            }
        })
    } catch (error) {
        return res.status(400).send(error)
    }
})

app.delete('/std/:id', (req, res)=> {
    let std_id = req.params.id
    
    try {
        dbCon.query('DELETE FROM student WHERE std_id = ?', std_id, (error, results, fields)=>{
            if(results["affectedRows"]) {
                return res.send({
                    success : true,
                    message: "Student has been deleted successfully !"
                })
            }else {
                return res.send({
                    success : true,
                    message: "nothing has been deleted"
                })
            }
        })
    } catch (error) {
        return res.status(400).send(error)
    }
})


// Assignment 8
app.get('/allEmp', (req, res) => {
    dbCon.query('SELECT * FROM employee', (error, results, fields) => {
        if (error) throw error
        return res.send(results)
    })
})

app.post('/emp', (req, res) => {
    var emp = req.body
    console.log(emp)
    if(!Object.keys(emp).length) {
        return res.status(400).send({error: true, message: 'Please provide employee'})
    }
    try {
        dbCon.query('INSERT into employee SET ?', emp, (error, results, fields) => {
            if (error) {
                return res.status(200).json({
                    error: "Please correct input"
                })
            }
            return res.send(results)
        })
    } catch (error) {
        return res.status(400).send({error:true, message: 'Please correct input'})
    }
})


app.post('/delEmp', (req, res) => {
    var emp = req.body
    console.log(emp)
    if(!Object.keys(emp).length) {
        return res.status(400).send({error: true, message: 'Please provide employee'})
    }
    try {
        dbCon.query('DELETE FROM employee WHERE ?', emp, (error, results, fields) => {
            if (error) {
                return res.status(200).json({
                    error: "Please correct input"
                })
            }
            return res.send(results)
        })
    } catch (error) {
        return res.status(400).send({error:true, message: 'Something error'})
    }
})

// Assignmnet 9
app.delete('/emp/:id', (req, res) => {
    let emp_id = req.params.id
    try {
        dbCon.query('DELETE FROM employee WHERE emp_id = ?', emp_id, (error, results, fields) => {
            if(error) {
                return res.status(400).send(error)
            }
            if(results["affectedRows"]) {
                return res.send({
                    success : true,
                    message: "Employee has been deleted successfully !"
                })
            }else {
                return res.send({
                    success : true,
                    message: "nothing has been deleted"
                })
            }
        })
    } catch (error) {
        return res.status(400).send(error)
    }
})

app.put('/emp/:id', (req, res) => {
    let emp_id = req.params.id
    let emp = req.body
    try {
        dbCon.query('UPDATE employee SET ? WHERE emp_id = ?', [emp, emp_id], (error, results, fields) => {
            if(error) {
                return res.status(400).send(error)
            }
            if(results["affectedRows"]) {
                return res.send({
                    success : true,
                    message: "Employee has been updated successfully"
                })
            }else {
                return res.send({
                    success : true,
                    message: "nothing has been updated"
                })
            }
        })
    } catch (error) {
        return res.status(400).send(error)
    }
})

app.get('/emp/:id', (req, res) => {
    let emp_id = req.params.id
    dbCon.query('SELECT * FROM employee WHERE emp_id = ?', emp_id, (error, results, fields) => {
        if(error) {
            return res.status(400).send(error)
        }else {
            return res.status(200).send(results[0])
        }
    })
})

//Lab 10
app.get('/allRegister', (req, res) => {
    dbCon.query('SELECT * FROM register_student', (error, results, fields)=> {
        if(error) {
            return res.status(400).send(error)
        }
        return res.send(results)
    })
})

app.get('/login/:id/:pass', async (req, res)=>{
    let std_id = req.params.id
    let std_password = req.params.pass
    if(!std_id || !std_password) {
        return res.status(400).send({
            error: user,
            message: "Please provide student id or password."
        })
    }
    dbCon.query('SELECT * FROM register_student WHERE std_id = ?', [std_id], (error, results, fields)=>{
        // console.log(results[0])
        if(error) {
            return res.status(400).send(error)
        }
        if(results[0]) {
            bcrypt.compare(std_password, results[0].std_password, (error, result)=>{
                if(error) {
                    return res.status(400).send({
                        "error" : error,
                        "message" : 'Incorrect password please try again'
                    })
                }
                if(result) {
                    return res.send({
                        "success" : 1,
                        "std_id" : results[0].std_id,
                        "std_name" : results[0].std_name,
                        "std_gender" : results[0].std_gender,
                        "role" : results[0].role
                    })
                }else {
                    return res.send({
                        "success" : 0,
                        "message" : 'Incorrect password please try again'
                    })
                }
            })
        }
        // return res.send(results)
    })
})

app.get('/search/:id', (req, res)=>{
    let std_id = req.params.id
    if(!std_id) {
        return res.status(400).send({
            error: user,
            message: "Please provide student id"
        })
    }
    dbCon.query('SELECT * FROM register_student WHERE std_id = ?', [std_id], (error, results, fields)=>{
        if(error) {
            return res.status(400).send({
                "error" : error,
                "message" : ''
            })
        }
        if(results[0]) {
            return res.send({
                "success" : 1,
                "std_id" : results[0].std_id,
                "std_name" : results[0].std_name,
                "std_gender" : results[0].std_gender,
                "role" : results[0].role
            })
        }else {
            return res.status(400).send({
                error: true,
                message: 'Student id not found!'
            })
        }
    })
})

app.post('/insertAccount', async (req, res)=>{
    let post = req.body
    let std_id = post.std_id
    let std_name = post.std_name
    let std_gender = post.std_gender
    let std_password = post.std_password
    let role = post.role
    const salt = await bcrypt.genSalt(10)
    let password_hash = await bcrypt.hash(std_password, salt)
    if(!post) {
        return res.status(400).send({
            error : true,
            message : 'Please provide a student data'
        })
    }
    
    dbCon.query('SELECT * FROM register_student WHERE std_id = ?', [std_id], (error, results, fields)=>{
        if(error) {
            return res.status(400).send({
                "error" : error,
                "message" : ''
            })
        }
        if(results[0]){
            return res.status(400).send({
                error: true,
                message: 'The student id is already in database.'
            })
        }else {
            if(!role) {
                var insertData = `INSERT INTO register_student(std_id, std_name, std_password, std_gender) VALUES("${std_id}", "${std_name}", "${password_hash}", "${std_gender}")`
            }else {
                var insertData = `INSERT INTO register_student(std_id, std_name, std_password, std_gender, role) VALUES("${std_id}", "${std_name}", "${password_hash}", "${std_gender}", "admin")`
            }
        }
        // console.log(insertData)
        dbCon.query(insertData, (error, results) => {
            if(error) {
                return res.status(400).send({
                    "error" : error,
                    "message" : ''
                })
            }
            return res.send(results)
        })
    })
})

// เปิด port
port = 3000
app.listen(port, () => {
    console.log('Node App is Running port '+port)
})