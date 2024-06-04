package br.com.javaricci;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = DIRECTORY_NAME + "/funcionarios.json";
    private List<Funcionario> funcionarios;

    public FuncionarioDAO() {
        funcionarios = new ArrayList<>();
        createDirectoryAndFile();
        loadFromFile();
    }

    private void createDirectoryAndFile() {
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // Inicia com um array JSON vazio
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFromFile() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Object obj = parser.parse(reader);
            JSONArray funcList = (JSONArray) obj;

            for (Object funcObj : funcList) {
                JSONObject funcJSON = (JSONObject) funcObj;
                Funcionario func = new Funcionario();
                func.setIdFunc(((Long) funcJSON.get("idfunc")).intValue());
                func.setNomeFunc((String) funcJSON.get("nomefunc"));
                func.setSalarioFunc((Double) funcJSON.get("salariofunc"));
                func.setHorasBaseFunc(((Long) funcJSON.get("horasbasefunc")).intValue());
                func.setSalHoraFunc((Double) funcJSON.get("salhorafunc"));
                func.setSalDiaFunc((Double) funcJSON.get("saldiafunc"));

                funcionarios.add(func);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        JSONArray funcList = new JSONArray();

        for (Funcionario func : funcionarios) {
            JSONObject funcJSON = new JSONObject();
            funcJSON.put("idfunc", func.getIdFunc());
            funcJSON.put("nomefunc", func.getNomeFunc());
            funcJSON.put("salariofunc", func.getSalarioFunc());
            funcJSON.put("horasbasefunc", func.getHorasBaseFunc());
            funcJSON.put("salhorafunc", func.getSalHoraFunc());
            funcJSON.put("saldiafunc", func.getSalDiaFunc());

            funcList.add(funcJSON);
        }

        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(funcList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFuncionario(Funcionario func) {
        func.setIdFunc(getNextId());
        funcionarios.add(func);
        saveToFile();
    }

    public void updateFuncionario(Funcionario func) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getIdFunc() == func.getIdFunc()) {
                funcionarios.set(i, func);
                saveToFile();
                return;
            }
        }
    }

    public void deleteFuncionario(int idFunc) {
        funcionarios.removeIf(func -> func.getIdFunc() == idFunc);
        saveToFile();
    }

    public Funcionario getFuncionarioById(int idFunc) {
        for (Funcionario func : funcionarios) {
            if (func.getIdFunc() == idFunc) {
                return func;
            }
        }
        return null;
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarios;
    }

    private int getNextId() {
        return funcionarios.stream().mapToInt(Funcionario::getIdFunc).max().orElse(0) + 1;
    }
}


/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private static final String FILE_NAME = "funcionarios.json";
    private List<Funcionario> funcionarios;

    public FuncionarioDAO() {
        funcionarios = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Object obj = parser.parse(reader);
            JSONArray funcList = (JSONArray) obj;

            for (Object funcObj : funcList) {
                JSONObject funcJSON = (JSONObject) funcObj;
                Funcionario func = new Funcionario();
                func.setIdFunc(((Long) funcJSON.get("idfunc")).intValue());
                func.setNomeFunc((String) funcJSON.get("nomefunc"));
                func.setSalarioFunc((Double) funcJSON.get("salariofunc"));
                func.setHorasBaseFunc(((Long) funcJSON.get("horasbasefunc")).intValue());
                func.setSalHoraFunc((Double) funcJSON.get("salhorafunc"));
                func.setSalDiaFunc((Double) funcJSON.get("saldiafunc"));

                funcionarios.add(func);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        JSONArray funcList = new JSONArray();

        for (Funcionario func : funcionarios) {
            JSONObject funcJSON = new JSONObject();
            funcJSON.put("idfunc", func.getIdFunc());
            funcJSON.put("nomefunc", func.getNomeFunc());
            funcJSON.put("salariofunc", func.getSalarioFunc());
            funcJSON.put("horasbasefunc", func.getHorasBaseFunc());
            funcJSON.put("salhorafunc", func.getSalHoraFunc());
            funcJSON.put("saldiafunc", func.getSalDiaFunc());

            funcList.add(funcJSON);
        }

        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(funcList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFuncionario(Funcionario func) {
        func.setIdFunc(getNextId());
        funcionarios.add(func);
        saveToFile();
    }

    public void updateFuncionario(Funcionario func) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getIdFunc() == func.getIdFunc()) {
                funcionarios.set(i, func);
                saveToFile();
                return;
            }
        }
    }

    public void deleteFuncionario(int idFunc) {
        funcionarios.removeIf(func -> func.getIdFunc() == idFunc);
        saveToFile();
    }

    public Funcionario getFuncionarioById(int idFunc) {
        for (Funcionario func : funcionarios) {
            if (func.getIdFunc() == idFunc) {
                return func;
            }
        }
        return null;
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarios;
    }

    private int getNextId() {
        return funcionarios.stream().mapToInt(Funcionario::getIdFunc).max().orElse(0) + 1;
    }
}
*/