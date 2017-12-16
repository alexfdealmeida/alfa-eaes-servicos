var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/apialocacoes');

var alocacoesSchema = new mongoose.Schema({
    id_tarefa: Number,
    id_aluno: Number
}, { collection: 'alocacoesSchema' }
);

module.exports = { Mongoose: mongoose, AlocacoesSchema: alocacoesSchema }