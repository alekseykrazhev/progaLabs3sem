package com.example.InternetShop;

import java.util.Scanner;
import java.io.IOException;
import java.util.MissingResourceException;

public class App
{
    public static void main(String[] args)
    {
        Goods A = new Goods(10, Type.pan);
        Goods B = new Goods(10, Type.plate);
        Goods C = new Goods(3, Type.spoon);
        Goods D = new Goods(200, Type.phone);
        Goods E = new Goods(100, Type.sneakers);
        Goods F = new Goods(100, Type.sneakers);
        Goods G = new Goods(10, Type.plate);
        Goods H = new Goods(200, Type.phone);
        Goods I = new Goods(3, Type.spoon);
        Goods J = new Goods(10, Type.pan);
        Goods K = new Goods(250, Type.phone);

        Goods Null = new Goods();

        Scanner in = new Scanner(System.in);
        try
        {
            if (args.length != 2)
            {
                in.close();
                throw new Exception("Invalid number of arguments");
            }
            System.out.println("Choose mode: 1 - print to file; 2 - read from file");
            int mode = in.nextInt();
            switch (mode)
            {
                case 1:
                    System.out.println("Created client array");
                    Client[] mas_Clients = new Client[]
                            {
                                    new Client("Арсений",500,new Order (1,A,B,I,H,F,G)),
                                    new Client("Наталья",100,new Order (2,C,D,Null,Null,Null,Null)),
                                    new Client("Данила",300,new Order (3,E,Null,Null,Null,Null,Null))
                            };

                    for (Client pr : mas_Clients) {
                        pr.print();
                    }

                    Connector connector = new Connector(mas_Clients);
                    connector.WriteAllDataToFile("Result");
                    break;
                case 2:
                    connector = new Connector();
                    connector.ReadAllDataFromFile("Result");
                    mas_Clients = connector.getMasClients();
                    System.out.println("Loaded client list");
                    for (Client pr : mas_Clients)
                    {
                        pr.print();
                    }

                    break;
                default:
                    in.close();
                    throw new IOException("Incorrect mode input!");
            }
            System.out.println("\n");
            Client client = new Client("Арнольд",50,new Order(4,J,Null,Null,Null,Null,Null));
            client.print();
            System.out.println("\n");

            Administrator admin1 = new Administrator("Виктор");


            System.out.println("Sale register");
            admin1.RegisterSale(client);
            client.print();


            System.out.println("Adding to blacklist");
            Client client0 = new Client("Вася",10,new Order(5,K,Null,Null,Null,Null,Null));
            client0.print();
            admin1.RegisterSale(client0);
            client0.print();
            System.out.println("\n");



        }
        catch (IOException e)
        {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        catch (MissingResourceException e)
        {
            System.err.println(e.getLocalizedMessage());
            System.exit(2);
        }
        catch (Exception e)
        {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }

        in.close();
    }
}
