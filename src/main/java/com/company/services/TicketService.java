package com.company.services;

import com.company.entities.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class TicketService implements TicketInterface, CSVReaderWriter<Ticket>{
    private ArrayList<Ticket> tickets = new ArrayList<>();

    private TicketService(){
        read();
    }
    private static TicketService instance;

    public static TicketService getInstance(){
        if(instance == null){
            instance = new TicketService();
        }
        return instance;
    }

    public ArrayList<Ticket> getTickets() {
        ArrayList<Ticket> ticketCopy = new ArrayList<>();
        ticketCopy.addAll(this.tickets);
        return ticketCopy;
    }

    public Ticket getTicketById(int index){
        Ticket ticket = null;
        for(int i = 0; i < this.tickets.size(); ++i){
            if(this.tickets.get(i).getId() == index){
                if(this.tickets.get(i) instanceof FirstClass){
                    if(this.tickets.get(i) instanceof BunkBed)
                        ticket =  (BunkBed) this.tickets.get(i);
                    ticket = (FirstClass) this.tickets.get(i);
                }
                else if (this.tickets.get(i) instanceof SecondClass){
                    ticket = (SecondClass) this.tickets.get(i);
                }
            }
        }
        return ticket;
    }

    public void updateTicket(int index, Ticket ticket){
        for(int i = 0; i < this.tickets.size(); ++i){
            if(this.tickets.get(i).getId() == index){
                this.tickets.remove(i);
                this.tickets.add(index, ticket);
                break;
            }
        }
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public void deteleTicket(int index){
        for(int i = 0; i < this.tickets.size(); ++i){
            if(this.tickets.get(i).getId() == index) {
                this.tickets.remove(i);
                break;
            }
        }
    }

    public Ticket readTicket() throws ParseException { // 0 = FirstClass; 1 = SecondClass; 2 = BunkBed
        Scanner scanner = new Scanner(System.in);
        RouteService routeService = RouteService.getInstance();
        System.out.println("0 - First Class");
        System.out.println("1 - Second Class");
        System.out.println("2 - Bunk Bed");
        int option = scanner.nextInt();
        if(option == 0){
            FirstClass firstClass = new FirstClass();
//            System.out.println("id = ");
//            firstClass.setId(scanner.nextInt());

            firstClass.setId(getMaxId() + 1);

            System.out.println("price = ");
            firstClass.setPrice(scanner.nextDouble());

            System.out.println("seat = ");
            firstClass.setSeat(scanner.nextInt());

            System.out.println("Route = ");
            Route route = routeService.readRoute();
            firstClass.setRoute(route);
            routeService.addRoute(route);

            System.out.println("number of meals included = ");
            int nr = scanner.nextInt();
            System.out.println("meals included = ");
            ArrayList arr = new ArrayList<>();
            for(int i = 0; i < nr; ++i){
                arr.add(scanner.next());
            }
            TreeSet t = new TreeSet(arr);
            firstClass.setMealsIncluded(t);

            return firstClass;

        } else if (option == 1){
            SecondClass secondClass = new SecondClass();

//            System.out.println("id = ");
//            secondClass.setId(scanner.nextInt());

            secondClass.setId(getMaxId() + 1);

            System.out.println("price = ");
            secondClass.setPrice(scanner.nextDouble());

            System.out.println("seat = ");
            secondClass.setSeat(scanner.nextInt());

            System.out.println("route = ");
            secondClass.setRoute(routeService.readRoute());

            System.out.println("discount = ");
            secondClass.setDiscount(scanner.nextDouble());

            return secondClass;
        } else {
            BunkBed bunkBed = new BunkBed();

//            System.out.println("id = ");
//            bunkBed.setId(scanner.nextInt());

            bunkBed.setId(getMaxId() + 1);

            System.out.println("price = ");
            bunkBed.setPrice(scanner.nextDouble());

            System.out.println("seat = ");
            bunkBed.setSeat(scanner.nextInt());

            System.out.println("route = ");
            bunkBed.setRoute(routeService.readRoute());

            System.out.println("top bed = true & bottom bed = false ");
            bunkBed.setBedPosition(scanner.nextBoolean());

            return bunkBed;
        }
    }

    @Override
    public String getAntet() {
        return null;
    }

    public int getMaxId() {
        int max = 0;
        for (int i = 0; i < tickets.size(); ++i) {
            if (tickets.get(i).getId() > max) {
                max = tickets.get(i).getId();
            }
        }
        return max;
    }

    @Override
    public Ticket processLine(String line) throws ParseException {
        return null;
    }

    public FirstClass processFirstClass(String line){
        //Id,Price,Seat,Route Id
        //1,100,22,1
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
        double price = Double.parseDouble(fields[1]);

        int seat = Integer.parseInt(fields[2]);

        int routeID = Integer.parseInt(fields[3]);

        RouteService routeService = RouteService.getInstance();
        Route route = new Route();
        route.setId(-1);
        try{
            route = routeService.getRouteById(routeID);
        } catch (Exception e){
            System.out.println("The route doesnt exist");
        }

        return new FirstClass(id, price, seat, route, new TreeSet<>());
    }

    public SecondClass processSecondClass(String line){
        //Id,Price,Seat,Route Id,Discount
        //11,40,2,3,20
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
        double price = Double.parseDouble(fields[1]);

        int seat = Integer.parseInt(fields[2]);

        int routeID = Integer.parseInt(fields[3]);

        double discount = Double.parseDouble(fields[4]);

        RouteService routeService = RouteService.getInstance();
        Route route = new Route();
        route.setId(-1);
        try{
            route = routeService.getRouteById(routeID);
        } catch (Exception e){
            System.out.println("The route doesnt exist");
        }

        return new SecondClass(id, price, seat, route, discount);
    }

    public BunkBed processBunkBed(String line){
        //Id,Price,Seat,Route Id,Bed Position
        //6,250,17,3,TRUE
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
        double price = Double.parseDouble(fields[1]);

        int seat = Integer.parseInt(fields[2]);

        int routeID = Integer.parseInt(fields[3]);

        boolean bedPosition = Boolean.parseBoolean(fields[4].toLowerCase());

        RouteService routeService = RouteService.getInstance();
        Route route = new Route();
        route.setId(-1);
        try{
            route = routeService.getRouteById(routeID);
        } catch (Exception e){
            System.out.println("The route doesnt exist");
        }

        return new BunkBed(id, price, seat, route, new ArrayList<>(), bedPosition);
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public String convertObjectToString(Ticket object) {
        String line = null;
        if(object instanceof FirstClass){
            if(object instanceof BunkBed) {
                //Id,Price,Seat,Route Id,Bed Position
                //6,250,17,3,TRUE
                String res = "FALSE";
                BunkBed bunkBed = (BunkBed) object;
                if(bunkBed.isBedPosition()){
                    res = "TRUE";
                }
                line =    bunkBed.getId() + separator
                        + bunkBed.getPrice() + separator
                        + bunkBed.getSeat() + separator
                        + bunkBed.getRoute().getId() + separator
                        + res + "\n";
            } else {
                //Id,Price,Seat,Route Id
                //1,100,22,1
                FirstClass firstClass = (FirstClass) object;
                line =    firstClass.getId() + separator
                        + firstClass.getPrice() + separator
                        + firstClass.getSeat() + separator
                        + firstClass.getRoute().getId() + "\n";
            }
        } else if(object instanceof SecondClass){
            //Id,Price,Seat,Route Id,Discount
            //11,40,2,3,20
            SecondClass secondClass = (SecondClass) object;
            line =    secondClass.getId() + separator
                    + secondClass.getPrice() + separator
                    + secondClass.getSeat() + separator
                    + secondClass.getRoute().getId() + separator
                    + secondClass.getDiscount() + "\n";
        }
        return line;
    }

    @Override
    public void initList(List<Ticket> objects) {
        tickets = new ArrayList<Ticket>(objects);
    }

    public List<FirstClass> readFirstClass(){
        String fileName = "src/main/java/com/company/resources/data - FirstClass.csv";
        File file = new File(fileName);
        String extraFileName = "src/main/java/com/company/resources/data - FirstClass_Info.csv";
        File extraFile = new File(extraFileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<FirstClass> result;

            try {
                List<FirstClass> resultLines = new ArrayList<FirstClass>();
                bufferedReader.readLine(); // skip first line
                String currentLine = bufferedReader.readLine();

                while (true) {
                    if (currentLine == null) {
                        result = resultLines;
                        break;
                    }
                    FirstClass obj = processFirstClass(currentLine);
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
                        FirstClass firstClass = resultLines.stream()
                                .filter(r -> r.getId() == id)
                                .findAny()
                                .orElse(null);
                        if(firstClass != null){
                            if(firstClass.getMealsIncluded().size() == 0){
                                List<String> materials = new ArrayList<String>();
                                materials.add(fields[1]);
                                firstClass.setMealsIncluded(new TreeSet<>(materials));
                            } else {
                                TreeSet<String> objs = firstClass.getMealsIncluded();
                                objs.add(fields[1]);
                                firstClass.setMealsIncluded((TreeSet<String>)objs);
                            }

                            int index = 0;
                            for(FirstClass element : resultLines){
                                if(element.getId() == firstClass.getId()){
                                    resultLines.set(index, firstClass);
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

    public List<BunkBed> readBunkBed(){
        String fileName = "src/main/java/com/company/resources/data - BunkBed.csv";
        File file = new File(fileName);
        String extraFileName = "src/main/java/com/company/resources/data - BunkBed_Info.csv";
        File extraFile = new File(extraFileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<BunkBed> result;

            try {
                List<BunkBed> resultLines = new ArrayList<BunkBed>();
                bufferedReader.readLine(); // skip first line
                String currentLine = bufferedReader.readLine();

                while (true) {
                    if (currentLine == null) {
                        result = resultLines;
                        break;
                    }
                    BunkBed obj = processBunkBed(currentLine);
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
                        BunkBed bunkBed = resultLines.stream()
                                .filter(r -> r.getId() == id)
                                .findAny()
                                .orElse(null);
                        if(bunkBed != null){
                            if(bunkBed.getMealsIncluded().size() == 0){
                                List<String> materials = new ArrayList<String>();
                                materials.add(fields[1]);
                                bunkBed.setMealsIncluded(new TreeSet<>(materials));
                            } else {
                                TreeSet<String> objs = bunkBed.getMealsIncluded();
                                objs.add(fields[1]);
                                bunkBed.setMealsIncluded((TreeSet<String>)objs);
                            }

                            int index = 0;
                            for(BunkBed element : resultLines){
                                if(element.getId() == bunkBed.getId()){
                                    resultLines.set(index, bunkBed);
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

    public List<SecondClass> readSecondClass(){
        String fileName = "src/main/java/com/company/resources/data - SecondClass.csv";
        File file = new File(fileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<SecondClass> result;

            try {
                List<SecondClass> resultLines = new ArrayList<SecondClass>();
                bufferedReader.readLine(); // skip first line
                String currentLine = bufferedReader.readLine();

                while (true) {
                    if (currentLine == null) {
                        result = resultLines;
                        break;
                    }
                    SecondClass obj = processSecondClass(currentLine);
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


    public List<Ticket> read() {
        List<BunkBed> bunkBeds = readBunkBed();
        List<FirstClass> firstClasses = readFirstClass();
        List<SecondClass> secondClasses = readSecondClass();
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(bunkBeds);
        tickets.addAll(firstClasses);
        tickets.addAll(secondClasses);
        initList(tickets);
        return tickets;
    }

    @Override
    public void write(List<Ticket> objects){
        String fileName = "src/main/java/com/company/resources/data - BunkBed.csv";
        File file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Price,Seat,Route Id,Bed Position\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Ticket element : objects){
                    if(element instanceof BunkBed bunkBed){
                        try{
                            String CSVline = this.convertObjectToString(bunkBed);
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
        fileName = "src/main/java/com/company/resources/data - BunkBed_Info.csv";
        file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Meal\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Ticket object : objects){
                    if(object instanceof BunkBed bunkBed){
                        TreeSet<String> materials = bunkBed.getMealsIncluded();
                        List<String> meals = new ArrayList<>(materials);
                        int nr = meals.size();
                        while(nr > 0){
                            String CSVline = bunkBed.getId() + separator;
                            if(materials.size() > 0){
                                CSVline += meals.get(0);
                                meals.remove(0);
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

        fileName = "src/main/java/com/company/resources/data - FirstClass.csv";
        file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Price,Seat,Route Id\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Ticket element : objects){
                    if(element instanceof FirstClass firstClass){
                        if(!(firstClass  instanceof BunkBed)){
                            try{
                                String CSVline = this.convertObjectToString(firstClass);
                                bufferedWriter.write(CSVline);
                            } catch (Throwable anything){
                                throw anything;
                            }
                        }
                    }

                }

            }
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        fileName = "src/main/java/com/company/resources/data - FirstClass_Info.csv";
        file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Meal\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Ticket object : objects){
                    if(object instanceof FirstClass firstClass){
                        if(!(firstClass  instanceof BunkBed)){
                            TreeSet<String> materials = firstClass.getMealsIncluded();
                            List<String> meals = new ArrayList<>(materials);
                            int nr = meals.size();
                            while(nr > 0){
                                String CSVline = firstClass.getId() + separator;
                                if(materials.size() > 0){
                                    CSVline += meals.get(0);
                                    meals.remove(0);
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

            }
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String fileName1 = "src/main/java/com/company/resources/data - SecondClass.csv";
        File file1 = new File(fileName1);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, false));
            try{
                String CSVline = "Id,Price,Seat,Route Id,Discount\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Ticket element : objects){
                    if(element instanceof SecondClass secondClass){
                        try{
                            String CSVline = this.convertObjectToString(secondClass);
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
