package com.company.services;

import com.company.entities.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class TrainService implements TrainInterface, CSVReaderWriter<Train>{
    private ArrayList<Train> trains = new ArrayList<>();

    private TrainService() {
        read();
    }
    private static TrainService instance;

    public static TrainService getInstance(){
        if(instance == null){
            instance = new TrainService();
        }
        return instance;
    }

    public ArrayList<Train> getTrains() {
        ArrayList<Train> trainsCopy = new ArrayList<>();
        trainsCopy.addAll(this.trains);
        return trainsCopy;
    }

    public Train getTicketById(int index){
        Train train = null;
        for(int i = 0; i < this.trains.size(); ++i){
            if(this.trains.get(i).getId() == index){
                if(this.trains.get(i) instanceof Boogie){
                    train = (Boogie) this.trains.get(i);
                }
                else if (this.trains.get(i) instanceof PassengerTrain){
                    train = (PassengerTrain) this.trains.get(i);
                }
            }
        }
        return train;
    }

    public void updateTrain(int index, Train train){
        for(int i = 0; i < this.trains.size(); ++i){
            if(this.trains.get(i).getId() == index){
                this.trains.remove(i);
                this.trains.add(index, train);
                break;
            }
        }
    }

    public void addTrain(Train train){
        this.trains.add(train);
    }

    public void deteleTrain(int index){
        for(int i = 0; i < this.trains.size(); ++i){
            if(this.trains.get(i).getId() == index) {
                this.trains.remove(i);
                break;
            }
        }
    }

    public Train readTrain(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("0 - Boogie");
        System.out.println("1 - Passenger Train");
        int option = scanner.nextInt();
        if(option == 0){
            Boogie boogie = new Boogie();
//            System.out.println("id = ");
//            boogie.setId(scanner.nextInt());

            boogie.setId(getMaxId() + 1);

            System.out.println("name = ");
            boogie.setName(scanner.next());

            System.out.println("speed = ");
            boogie.setSpeed(scanner.nextDouble());

            System.out.println("number of waggons = ");
            boogie.setNumberOWaggons(scanner.nextInt());

            System.out.println("fuel cost = ");
            boogie.setFuelCost(scanner.nextDouble());

            System.out.println("number of materials and quantities = ");
            int nr = scanner.nextInt();
            ArrayList<String> mat = new ArrayList<>();
            ArrayList<Double> qua = new ArrayList<>();
            System.out.println("materials and quantities = ");
            for(int i = 0; i < nr; ++i){
                System.out.println("material = ");
                mat.add(scanner.next());
                System.out.println("quantity = ");
                qua.add(scanner.nextDouble());
            }
            boogie.setMaterials(mat);
            boogie.setQuantity(qua);

            return boogie;
        } else {
            PassengerTrain passengerTrain = new PassengerTrain();
//            System.out.println("id = ");
//            passengerTrain.setId(scanner.nextInt());

            passengerTrain.setId(getMaxId() + 1);

            System.out.println("name = ");
            passengerTrain.setName(scanner.next());

            System.out.println("speed = ");
            passengerTrain.setSpeed(scanner.nextDouble());

            System.out.println("number of waggons = ");
            passengerTrain.setNumberOWaggons(scanner.nextInt());

            System.out.println("fuel cost = ");
            passengerTrain.setFuelCost(scanner.nextDouble());

            System.out.println("number of seats = ");
            passengerTrain.setNumberOfSeats(scanner.nextInt());

            System.out.println("number of classes = ");
            passengerTrain.setNumberOfClasses(scanner.nextInt());

            return passengerTrain;
        }
    }

    @Override
    public String getAntet() {
        return null;
    }

    public int getMaxId() {
        int max = 0;
        for (int i = 0; i < trains.size(); ++i) {
            if (trains.get(i).getId() > max) {
                max = trains.get(i).getId();
            }
        }
        return max;
    }

    @Override
    public Train processLine(String line) throws ParseException {
        return null;
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public String convertObjectToString(Train object) {
        String line = null;
        if(object instanceof Boogie boogie)
        {
            //Id,Name,Speed,Number of Waggons,Fuel Cost
            //1,B1215,150,20,200
            line = boogie.getId() + separator + boogie.getName() + separator + boogie.getSpeed() + separator + boogie.getNumberOWaggons() + separator + boogie.getFuelCost() + "\n";
        } else if (object instanceof PassengerTrain passengerTrain) {
            //Id,Name,Speed,Number of Waggons,Fuel Cost,Number of Seats,Number of Classes
            //6,IR1352,30,30,200,600,1
            line = passengerTrain.getId() + separator + passengerTrain.getName() + separator + passengerTrain.getSpeed() + separator + passengerTrain.getNumberOWaggons()
                    + separator + passengerTrain.getFuelCost() + separator + passengerTrain.getNumberOfSeats()
                    + separator + passengerTrain.getNumberOfClasses() + "\n";
        }
        return line;
    }

    @Override
    public void initList(List<Train> objects) {
        trains = new ArrayList<Train>(objects);
    }


    public Boogie processBoogie(String line){
        // Id,Name,Speed,Number of Waggons,Fuel Cost
        // 1,B1215,150,20,200
        String[] fields = line.split(separator);
        int id = 0;
        if(Objects.equals(fields[0], "null")){
            id = getMaxId();
        } else {
            try {
                id = Integer.parseInt(fields[0]);
            } catch (Exception e) {
                System.out.println("The id must be an int");
            }
        }
        String name = fields[1];

        double speed = Double.parseDouble(fields[2]);

        int numberOfWagons = Integer.parseInt(fields[3]);

        double fuelCost = Double.parseDouble(fields[4]);

        return new Boogie(id, name, speed, numberOfWagons, fuelCost, new ArrayList<>(), new ArrayList<>());
    }

    public PassengerTrain processPassengerTrain(String line){
        //Id,Name,Speed,Number of Waggons,Fuel Cost,Number of Seats,Number of Classes
        //6,IR1352,30,30,200,600,1
        String[] fields = line.split(separator);
        int id = 0;
        if(Objects.equals(fields[0], "null")){
            id = getMaxId();
        } else {
            try {
                id = Integer.parseInt(fields[0]);
            } catch (Exception e) {
                System.out.println("The id must be an int");
            }
        }
        String name = fields[1];

        double speed = Double.parseDouble(fields[2]);

        int numberOfWagons = Integer.parseInt(fields[3]);

        double fuelCost = Double.parseDouble(fields[4]);

        int numberOfSeats = Integer.parseInt(fields[5]);

        int numberOfClasses = Integer.parseInt(fields[6]);

        return new PassengerTrain(id, name, speed, numberOfWagons, fuelCost, numberOfSeats, numberOfClasses);
    }

    public List<Boogie> readBoogies(){
        String fileName = "src/main/java/com/company/resources/data - Boogie.csv";
        File file = new File(fileName);
        String extraFileName = "src/main/java/com/company/resources/data - Boogie_Info.csv";
        File extraFile = new File(extraFileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<Boogie> result;

            try {
                List<Boogie> resultLines = new ArrayList<Boogie>();
                bufferedReader.readLine(); // skip first line
                String currentLine = bufferedReader.readLine();

                while (true) {
                    if (currentLine == null) {
                        result = resultLines;
                        break;
                    }
                    Boogie obj = processBoogie(currentLine);
                    resultLines.add(obj);
                    currentLine = bufferedReader.readLine();
                }
                BufferedReader extra = new BufferedReader(new FileReader(extraFile));
                try{
                    extra.readLine();
                    String line = extra.readLine();
                    while (true) {
                        if (line == null) {
                            break;
                        }
                        String[] fields = line.split(separator);
                        int id = Integer.parseInt(fields[0]);
                        Boogie boogie = resultLines.stream()
                                .filter(r -> r.getId() == id)
                                .findAny()
                                .orElse(null);
                        if(boogie != null){
                            if(boogie.getMaterials().size() == 0){
                                List<String> materials = new ArrayList<String>();
                                materials.add(fields[1]);
                                boogie.setMaterials((ArrayList<String>) materials);
                            } else {
                                List<String> objs = boogie.getMaterials();
                                objs.add(fields[1]);
                                boogie.setMaterials((ArrayList<String>) objs);
                            }

                            if(boogie.getQuantity().size() == 0){
                                List<Double> materials = new ArrayList<Double>();
                                materials.add(Double.parseDouble(fields[2]));
                                boogie.setQuantity((ArrayList<Double>) materials);
                            } else {
                                List<Double> objs = boogie.getQuantity();
                                objs.add(Double.parseDouble(fields[2]));
                                boogie.setQuantity((ArrayList<Double>) objs);
                            }

                            int index = 0;
                            for(Boogie element : resultLines){
                                if(element.getId() == boogie.getId()){
                                    resultLines.set(index, boogie);
                                    break;
                                }
                                index += 1;
                            }
                        }

                        line = extra.readLine();
                    }
                } catch (Throwable t){
                    try {
                        extra.close();
                    } catch (Throwable s) {
                        t.addSuppressed(s);
                    }
                    throw t;
                }
                result = resultLines;

            } catch (Throwable anything) {
                try {
                    bufferedReader.close();
                } catch (Throwable something) {
                    anything.addSuppressed(something);
                }
                throw anything;
            }

            //bufferedReader.close();
            //initList(result);
            return result;
        } catch (IOException e1) {
            System.out.println("File not found");
            initList(Collections.emptyList());
            return Collections.emptyList();
        }
    }

    public List<PassengerTrain> readPassangerTrain(){
        String fileName = "src/main/java/com/company/resources/data - PassengerTrain.csv";
        File file = new File(fileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<PassengerTrain> result;

            try {
                List<PassengerTrain> resultLines = new ArrayList<PassengerTrain>();
                bufferedReader.readLine(); // skip first line
                String currentLine = bufferedReader.readLine();

                while (true) {
                    if (currentLine == null) {
                        result = resultLines;
                        break;
                    }
                    PassengerTrain obj = processPassengerTrain(currentLine);
                    resultLines.add(obj);
                    currentLine = bufferedReader.readLine();
                }
            } catch (Throwable anything) {
                try {
                    bufferedReader.close();
                } catch (Throwable something) {
                    anything.addSuppressed(something);
                }
                throw anything;
            }

            //bufferedReader.close();
            //initList(result);
            return result;
        } catch (FileNotFoundException e1) {
            System.out.println("File not found");
            initList(Collections.emptyList());
            return Collections.emptyList();
        } catch (IOException e2) {
            System.out.println("Cannot read from file");
            initList(Collections.emptyList());
            return Collections.emptyList();
        }
    }


    public List<Train> read() {
        List<Boogie> boogies = readBoogies();
        List<PassengerTrain> passengerTrains = readPassangerTrain();
        List<Train> trains = new ArrayList<>();
        trains.addAll(boogies);
        trains.addAll(passengerTrains);
        initList(trains);
        return trains;
    }

    @Override
    public void write(List<Train> objects){
        String fileName = "src/main/java/com/company/resources/data - Boogie.csv";
        File file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Name,Speed,Number of Waggons,Fuel Cost\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Train train : objects){
                    if(train instanceof Boogie boogie){
                        try{
                            String CSVline = this.convertObjectToString(boogie);
                            bufferedWriter.write(CSVline);
                        } catch (Throwable anything){
                            throw anything;
                        }
                    }

                }

            }
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        fileName = "src/main/java/com/company/resources/data - Boogie_Info.csv";
        file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Material,Quantity\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Train object : objects){
                    if(object instanceof Boogie boogie){
                        List<String> materials = boogie.getMaterials();
                        List<Double> quantity = boogie.getQuantity();
                        int nr = materials.size();
                        while(nr > 0){
                            String CSVline = boogie.getId() + separator;
                            if(materials.size() > 0){
                                CSVline += materials.get(0) + separator;
                                materials.remove(0);
                            } else {
                                CSVline += "null" + separator;
                            }
                            if(quantity.size() > 0){
                                CSVline += quantity.get(0);
                                quantity.remove(0);
                            } else {
                                CSVline += "null";
                            }

                            try{
                                CSVline +=  "\n";
                                bufferedWriter.write(CSVline);
                            } catch (Throwable anything){
                                throw anything;
                            }
                            nr -= 1;
                        }
                    }

                }

            }
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String fileName1 = "src/main/java/com/company/resources/data - PassengerTrain.csv";
        File file1 = new File(fileName1);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, false));
            try{
                String CSVline = "Id,Name,Speed,Number of Waggons,Fuel Cost,Number of Seats,Number of Classes\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Train train : objects){
                    if(train instanceof PassengerTrain passengerTrain){
                        try{
                            String CSVline = this.convertObjectToString(passengerTrain);
                            bufferedWriter.write(CSVline);
                        } catch (Throwable anything){
                            throw anything;
                        }
                    }

                }

            }
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
