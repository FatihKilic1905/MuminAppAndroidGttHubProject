package com.fatihkilic.muminappandroid.User;

import java.util.ArrayList;

public class PickerListState {


    private static ArrayList<PickerListState> pickerListStateArrayList;

    private int id;
    private String state;


    public PickerListState(int id, String state) {
        this.id = id;
        this.state = state;
    }

    public static void initStateList () {

        pickerListStateArrayList = new ArrayList<>();

        PickerListState adana = new PickerListState(0,"Adana");
        pickerListStateArrayList.add(adana);

        PickerListState adiyaman = new PickerListState(1,"Adıyaman");
        pickerListStateArrayList.add(adiyaman);

        PickerListState afyon = new PickerListState(2,"Afyonkarahisar");
        pickerListStateArrayList.add(afyon);

        PickerListState agri = new PickerListState(3,"Ağrı");
        pickerListStateArrayList.add(agri);

        PickerListState amasya = new PickerListState(4,"Amasya");
        pickerListStateArrayList.add(amasya);

        PickerListState ankara = new PickerListState(5,"Ankara");
        pickerListStateArrayList.add(ankara);

        PickerListState antalya = new PickerListState(6,"Antalya");
        pickerListStateArrayList.add(antalya);

        PickerListState artvin = new PickerListState(7,"Artvin");
        pickerListStateArrayList.add(artvin);

        PickerListState aydin = new PickerListState(8,"Aydın");
        pickerListStateArrayList.add(aydin);

        PickerListState balikesir = new PickerListState(9,"Balıkesir");
        pickerListStateArrayList.add(balikesir);

        PickerListState bilecik = new PickerListState(10,"Bilecik");
        pickerListStateArrayList.add(bilecik);

        PickerListState bingol = new PickerListState(11,"Bingöl");
        pickerListStateArrayList.add(bingol);

        PickerListState bitlis = new PickerListState(12,"Bitlis");
        pickerListStateArrayList.add(bitlis);

        PickerListState bolu = new PickerListState(13,"Bolu");
        pickerListStateArrayList.add(bolu);

        PickerListState Burdur = new PickerListState(14,"Burdur");
        pickerListStateArrayList.add(Burdur);

        PickerListState Bursa = new PickerListState(15,"Bursa");
        pickerListStateArrayList.add(Bursa);

        PickerListState canakkale = new PickerListState(16,"Çanakkale");
        pickerListStateArrayList.add(canakkale);

        PickerListState cankiri = new PickerListState(17,"Çankırı");
        pickerListStateArrayList.add(cankiri);

        PickerListState corum = new PickerListState(18,"Çorum");
        pickerListStateArrayList.add(corum);

        PickerListState denizli = new PickerListState(19,"Denizli");
        pickerListStateArrayList.add(denizli);

        PickerListState diyarbakir = new PickerListState(20,"Diyarbakır");
        pickerListStateArrayList.add(diyarbakir);

        PickerListState edirne = new PickerListState(21,"Edirne");
        pickerListStateArrayList.add(edirne);

        PickerListState elazig = new PickerListState(22,"Elazığ");
        pickerListStateArrayList.add(elazig);

        PickerListState erzincan = new PickerListState(23,"Erzincan");
        pickerListStateArrayList.add(erzincan);

        PickerListState erzurum = new PickerListState(24,"Erzurum");
        pickerListStateArrayList.add(erzurum);

        PickerListState eskisehir = new PickerListState(25,"Eskişehir");
        pickerListStateArrayList.add(eskisehir);

        PickerListState antep = new PickerListState(26,"Gaziantep");
        pickerListStateArrayList.add(antep);

        PickerListState giresun = new PickerListState(27,"Giresun");
        pickerListStateArrayList.add(giresun);

        PickerListState gumushane = new PickerListState(28,"Gümüşhane");
        pickerListStateArrayList.add(gumushane);

        PickerListState hakkari = new PickerListState(29,"Hakkari");
        pickerListStateArrayList.add(hakkari);

        PickerListState hatay = new PickerListState(30,"Hatay");
        pickerListStateArrayList.add(hatay);

        PickerListState isparta = new PickerListState(31,"Isparta");
        pickerListStateArrayList.add(isparta);

        PickerListState mersin = new PickerListState(32,"Mersin");
        pickerListStateArrayList.add(mersin);

        PickerListState ist = new PickerListState(33,"İstanbul");
        pickerListStateArrayList.add(ist);

        PickerListState izmir = new PickerListState(34,"İzmir");
        pickerListStateArrayList.add(izmir);

        PickerListState kars = new PickerListState(35,"Kars");
        pickerListStateArrayList.add(kars);

        PickerListState kastamonu = new PickerListState(36,"Kastamonu");
        pickerListStateArrayList.add(kastamonu);

        PickerListState kayseri = new PickerListState(37,"Kayseri");
        pickerListStateArrayList.add(kayseri);

        PickerListState kirklareli = new PickerListState(38,"Kırklareli");
        pickerListStateArrayList.add(kirklareli);

        PickerListState kirsehir = new PickerListState(39,"Kırşehir");
        pickerListStateArrayList.add(kirsehir);

        PickerListState kocaeli = new PickerListState(40,"Kocaeli");
        pickerListStateArrayList.add(kocaeli);

        PickerListState konya = new PickerListState(41,"Konya");
        pickerListStateArrayList.add(konya);

        PickerListState kutahya = new PickerListState(42,"Kütahya");
        pickerListStateArrayList.add(kutahya);

        PickerListState malatya = new PickerListState(43,"Malatya");
        pickerListStateArrayList.add(malatya);

        PickerListState manisa = new PickerListState(44,"Manisa");
        pickerListStateArrayList.add(manisa);

        PickerListState kahramanmaras = new PickerListState(45,"Kahramanmaraş");
        pickerListStateArrayList.add(kahramanmaras);

        PickerListState mardin = new PickerListState(46,"Mardin");
        pickerListStateArrayList.add(mardin);

        PickerListState mugla = new PickerListState(47,"Muğla");
        pickerListStateArrayList.add(mugla);

        PickerListState mus = new PickerListState(48,"Muş");
        pickerListStateArrayList.add(mus);

        PickerListState nevsehir = new PickerListState(49,"Nevşehir");
        pickerListStateArrayList.add(nevsehir);

        PickerListState nigde = new PickerListState(50,"Niğde");
        pickerListStateArrayList.add(nigde);

        PickerListState ordu = new PickerListState(51,"Ordu");
        pickerListStateArrayList.add(ordu);

        PickerListState rize = new PickerListState(52,"Rize");
        pickerListStateArrayList.add(rize);

        PickerListState sakarya = new PickerListState(53,"Sakarya");
        pickerListStateArrayList.add(sakarya);

        PickerListState samsun = new PickerListState(54,"Samsun");
        pickerListStateArrayList.add(samsun);

        PickerListState siirt = new PickerListState(55,"Siirt");
        pickerListStateArrayList.add(siirt);

        PickerListState sinop = new PickerListState(56,"Sinop");
        pickerListStateArrayList.add(sinop);

        PickerListState sivas = new PickerListState(57,"Sivas");
        pickerListStateArrayList.add(sivas);

        PickerListState tekirdag = new PickerListState(58,"Tekirdağ");
        pickerListStateArrayList.add(tekirdag);

        PickerListState tokat = new PickerListState(59,"Tokat");
        pickerListStateArrayList.add(tokat);

        PickerListState trabzon = new PickerListState(60,"Trabzon");
        pickerListStateArrayList.add(trabzon);

        PickerListState tunceli = new PickerListState(61,"Tunceli");
        pickerListStateArrayList.add(tunceli);

        PickerListState sanliurfa = new PickerListState(62,"Şanlıurfa");
        pickerListStateArrayList.add(sanliurfa);

        PickerListState usak = new PickerListState(63,"Uşak");
        pickerListStateArrayList.add(usak);

        PickerListState van = new PickerListState(64,"Van");
        pickerListStateArrayList.add(van);

        PickerListState yozgat = new PickerListState(65,"Yozgat");
        pickerListStateArrayList.add(yozgat);

        PickerListState zonguldak = new PickerListState(66,"Zonguldak");
        pickerListStateArrayList.add(zonguldak);

        PickerListState aksaray = new PickerListState(67,"Aksaray");
        pickerListStateArrayList.add(aksaray);

        PickerListState bayburt = new PickerListState(68,"Bayburt");
        pickerListStateArrayList.add(bayburt);

        PickerListState karaman = new PickerListState(69,"Karaman");
        pickerListStateArrayList.add(karaman);

        PickerListState kirikkale = new PickerListState(70,"Kırıkkale");
        pickerListStateArrayList.add(kirikkale);

        PickerListState batman = new PickerListState(71,"Batman");
        pickerListStateArrayList.add(batman);

        PickerListState sirnak = new PickerListState(72,"Şırnak");
        pickerListStateArrayList.add(sirnak);

        PickerListState bartin = new PickerListState(73,"Bartın");
        pickerListStateArrayList.add(bartin);

        PickerListState ardahan = new PickerListState(74,"Ardahan");
        pickerListStateArrayList.add(ardahan);

        PickerListState igdir = new PickerListState(75,"Iğdır");
        pickerListStateArrayList.add(igdir);

        PickerListState yalova = new PickerListState(76,"Yalova");
        pickerListStateArrayList.add(yalova);

        PickerListState karabuk = new PickerListState(77,"Karabük");
        pickerListStateArrayList.add(karabuk);

        PickerListState kilis = new PickerListState(78,"Kilis");
        pickerListStateArrayList.add(kilis);

        PickerListState osmaniye = new PickerListState(79,"Osmaniye");
        pickerListStateArrayList.add(osmaniye);

        PickerListState duzce = new PickerListState(80,"Düzce");
        pickerListStateArrayList.add(duzce);




    }


    public static ArrayList<PickerListState> getPickerListStateArrayList() {
        return pickerListStateArrayList;
    }


    public static String[] stateNames() {

        String[] stateNames = new String[pickerListStateArrayList.size()];
        for(int i = 0; i < pickerListStateArrayList.size(); i++) {

            stateNames[i] = pickerListStateArrayList.get(i).state;

        }

        return stateNames;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}
