package br.com.fiap.demo.gs.service;

import java.util.List;

import br.com.fiap.demo.gs.data.UserDao;
import br.com.fiap.demo.gs.model.User;

public class UserService {

    static UserDao dao = new UserDao();

    public List<User> listarUsers() {
        return dao.listarUsers();
    }

    public User consultarUser(Long idUser) {
        return dao.consultarUser(idUser);
    }

    public boolean cadastraUser(User user) {
        if (!validar(user)) return false;
        dao.cadastraUser(user);
        return true;
    }

    private boolean validar(User user) {
        return user != null && user.getNome() != null && !user.getNome().isEmpty()
                && user.getEmail() != null && !user.getEmail().isEmpty();
    }

    public boolean deletaUser(Long idUser) {
        User user = dao.consultarUser(idUser);

        if (user != null) {
            dao.deletaUser(idUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaUser(Long idUser, User userAtualizado) {
        User userExistente = dao.consultarUser(idUser);

        if (userExistente != null) {
            userExistente.setNome(userAtualizado.getNome());
            userExistente.setSenha(userAtualizado.getSenha());
            userExistente.setEmail(userAtualizado.getEmail());
            userExistente.setGender(userAtualizado.getGender());
            userExistente.setAge(userAtualizado.getAge());
            userExistente.setOccupation(userAtualizado.getOccupation());

            dao.atualizaUser(userExistente);
            
            return true;
        } else {
            return false;
        }
    }
}
