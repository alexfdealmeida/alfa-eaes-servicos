export const baseUrlTarefas = 'http://localhost:8080/tarefas';
export const baseUrlUsuarios = 'http://localhost:8080/usuarios';
export const baseUrlMensageria = 'http://localhost:8800/messaging';

// api de usuários
export const usuariosAll_Get = '/usuarios/all';
export const usuariosSaveOrCreate_Post = '/usuarios/save-or-create';
export const usuariosRemove_Delete = '/usuarios/remove/{id}';
export const usuariosRemove_Delete_Par1 = '{id}';
export const usuariosNotificoes_WebSocket = '/usuarios-notifications';

// api de tarefas
export const tarefasAll_Get = '/tarefas/all';
export const tarefasSaveOrCreate_Post = '/tarefas/save-or-create';
export const tarefasRemove_Delete = '/tarefas/remove/{id}';
export const tarefasRemove_Delete_Par1 = '{id}';

// api de alocação de tarefas
export const usuarioTarefaByTarefaId_Get = '/usuarios-tarefas/find-by-tarefa-id/{idTarefa}';
export const usuarioTarefaByTarefaId_Get_Par1 = '{idTarefa}';
export const usuarioTarefaSaveOrCreate_Post = '/usuarios-tarefas/save-or-create';
export const usuarioTarefaRemove_Delete = '/usuarios-tarefas/remove/{id}';
export const usuarioTarefaRemove_Delete_Par1 = '{id}';
export const usuarioTarefaRemoveTarefasDoUsuario_Delete = '/usuarios-tarefas/remove-tarefas-do-usuario/{usuarioId}';
export const usuarioTarefaRemoveTarefasDoUsuario_Delete_Par1 = '{usuarioId}';
export const usuarioTarefaRemoveUsuariosDaTarefa_Delete = '/usuarios-tarefas/remove-usuarios-da-tarefa/{tarefaId}';
export const usuarioTarefaRemoveUsuariosDaTarefa_Delete_Par1 = '{tarefaId}';
