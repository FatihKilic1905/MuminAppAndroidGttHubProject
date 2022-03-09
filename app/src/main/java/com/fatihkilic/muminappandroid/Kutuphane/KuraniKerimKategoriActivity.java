package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.ZikirMatik.AdapterZikirlerim;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimKategoriBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityKuraniKerimMainBinding;

import java.util.ArrayList;

public class KuraniKerimKategoriActivity extends AppCompatActivity {


    private ActivityKuraniKerimKategoriBinding binding;

    ArrayList<ModelKuraniKerimKategori> modelKuraniKerimKategoriArrayList;
    AdapterKuraniKerimKategori adapterKuraniKerimKategori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurani_kerim_kategori);


        binding = ActivityKuraniKerimKategoriBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        modelKuraniKerimKategoriArrayList = new ArrayList<>();
        getSurelerList();
        binding.surelerRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapterKuraniKerimKategori = new AdapterKuraniKerimKategori(modelKuraniKerimKategoriArrayList);
        binding.surelerRecyclerview.setAdapter(adapterKuraniKerimKategori);













    }


    public void getSurelerList () {

        ModelKuraniKerimKategori Fatiha = new ModelKuraniKerimKategori("Fatiha",1);
        ModelKuraniKerimKategori Bakara = new ModelKuraniKerimKategori("Bakara",2);
        ModelKuraniKerimKategori aliimran = new ModelKuraniKerimKategori("Âl-i İmrân",3);
        ModelKuraniKerimKategori Nisa = new ModelKuraniKerimKategori("Nisâ",4);
        ModelKuraniKerimKategori maide = new ModelKuraniKerimKategori("Mâide",5);
        ModelKuraniKerimKategori Enam = new ModelKuraniKerimKategori("En'âm",6);
        ModelKuraniKerimKategori Araf = new ModelKuraniKerimKategori("A'râf",7);
        ModelKuraniKerimKategori Enfal = new ModelKuraniKerimKategori("Enfâl",8);
        ModelKuraniKerimKategori Tevbe = new ModelKuraniKerimKategori("Tevbe",9);
        ModelKuraniKerimKategori Yunus = new ModelKuraniKerimKategori("Yunus",10);
        ModelKuraniKerimKategori Hud = new ModelKuraniKerimKategori("Hûd",11);
        ModelKuraniKerimKategori Yusuf = new ModelKuraniKerimKategori("Yusuf",12);
        ModelKuraniKerimKategori Rad = new ModelKuraniKerimKategori("Ra'd",13);
        ModelKuraniKerimKategori i̇brahim = new ModelKuraniKerimKategori("İbrahim",14);
        ModelKuraniKerimKategori Hicr = new ModelKuraniKerimKategori("Hicr",15);
        ModelKuraniKerimKategori Nahl = new ModelKuraniKerimKategori("Nahl",16);
        ModelKuraniKerimKategori isra = new ModelKuraniKerimKategori("İsrâ",17);
        ModelKuraniKerimKategori Kehf = new ModelKuraniKerimKategori("Kehf",18);
        ModelKuraniKerimKategori Meryem = new ModelKuraniKerimKategori("Meryem",19);
        ModelKuraniKerimKategori Taha = new ModelKuraniKerimKategori("Tâhâ",20);
        ModelKuraniKerimKategori Enbiya = new ModelKuraniKerimKategori("Enbiyâ",21);
        ModelKuraniKerimKategori Hac = new ModelKuraniKerimKategori("Hac",22);
        ModelKuraniKerimKategori Muminun = new ModelKuraniKerimKategori("Mü'minûn",23);
        ModelKuraniKerimKategori Nur = new ModelKuraniKerimKategori("Nûr",24);
        ModelKuraniKerimKategori Furkan = new ModelKuraniKerimKategori("Furkan",25);
        ModelKuraniKerimKategori suara = new ModelKuraniKerimKategori("Şuarâ",26);
        ModelKuraniKerimKategori Neml = new ModelKuraniKerimKategori("Neml",27);
        ModelKuraniKerimKategori Kasas = new ModelKuraniKerimKategori("Kasas",28);
        ModelKuraniKerimKategori Ankebut = new ModelKuraniKerimKategori("Ankebût",29);
        ModelKuraniKerimKategori Rum = new ModelKuraniKerimKategori("Rûm",30);
        ModelKuraniKerimKategori Lokman = new ModelKuraniKerimKategori("Lokman",31);
        ModelKuraniKerimKategori Secde = new ModelKuraniKerimKategori("Secde",32);
        ModelKuraniKerimKategori Ahzab = new ModelKuraniKerimKategori("Ahzâb",33);
        ModelKuraniKerimKategori Sebe = new ModelKuraniKerimKategori("Sebe",34);
        ModelKuraniKerimKategori Fatir = new ModelKuraniKerimKategori("Fâtır",35);
        ModelKuraniKerimKategori Yasin = new ModelKuraniKerimKategori("Yâsin",36);
        ModelKuraniKerimKategori Saffat = new ModelKuraniKerimKategori("Sâffât",37);
        ModelKuraniKerimKategori sad = new ModelKuraniKerimKategori("Sâd",38);
        ModelKuraniKerimKategori zumer = new ModelKuraniKerimKategori("Zümer",39);
        ModelKuraniKerimKategori mumin = new ModelKuraniKerimKategori("Mü'min",40);
        ModelKuraniKerimKategori Fussilet = new ModelKuraniKerimKategori("Fussilet",41);
        ModelKuraniKerimKategori sura = new ModelKuraniKerimKategori("Şûrâ",42);
        ModelKuraniKerimKategori Zuhruf = new ModelKuraniKerimKategori("Zuhruf",43);
        ModelKuraniKerimKategori Duhan = new ModelKuraniKerimKategori("Duhân",44);
        ModelKuraniKerimKategori Casiye = new ModelKuraniKerimKategori("Câsiye",45);
        ModelKuraniKerimKategori Ahkaf = new ModelKuraniKerimKategori("Ahkaf",46);
        ModelKuraniKerimKategori Muhammed = new ModelKuraniKerimKategori("Muhammed",47);
        ModelKuraniKerimKategori Fetih = new ModelKuraniKerimKategori("Fetih",48);
        ModelKuraniKerimKategori Hucurat = new ModelKuraniKerimKategori("Hucurât",49);
        ModelKuraniKerimKategori Kaf = new ModelKuraniKerimKategori("Kaf",50);
        ModelKuraniKerimKategori Zariyat = new ModelKuraniKerimKategori("Zâriyât",51);
        ModelKuraniKerimKategori Tur = new ModelKuraniKerimKategori("Tûr",52);
        ModelKuraniKerimKategori Necm = new ModelKuraniKerimKategori("Necm",53);
        ModelKuraniKerimKategori Kamer = new ModelKuraniKerimKategori("Kamer",54);
        ModelKuraniKerimKategori Rahman = new ModelKuraniKerimKategori("Rahmân",55);
        ModelKuraniKerimKategori Vakia = new ModelKuraniKerimKategori("Vâkıa",56);
        ModelKuraniKerimKategori Hadid = new ModelKuraniKerimKategori("Hadid",57);
        ModelKuraniKerimKategori Mucadele = new ModelKuraniKerimKategori("Mücâdele",58);
        ModelKuraniKerimKategori Hasr = new ModelKuraniKerimKategori("Haşr",59);
        ModelKuraniKerimKategori Mumtehine = new ModelKuraniKerimKategori("Mümtehine",60);
        ModelKuraniKerimKategori Saf = new ModelKuraniKerimKategori("Saf",61);
        ModelKuraniKerimKategori Cuma = new ModelKuraniKerimKategori("Cum'a",62);
        ModelKuraniKerimKategori Munafikun = new ModelKuraniKerimKategori("Münâfikûn",63);
        ModelKuraniKerimKategori Tegabun = new ModelKuraniKerimKategori("Teğabün",64);
        ModelKuraniKerimKategori Talak = new ModelKuraniKerimKategori("Talâk",65);
        ModelKuraniKerimKategori Tahrim = new ModelKuraniKerimKategori("Tahrim",66);
        ModelKuraniKerimKategori Mulk = new ModelKuraniKerimKategori("Mülk",67);
        ModelKuraniKerimKategori Kalem = new ModelKuraniKerimKategori("Kalem",68);
        ModelKuraniKerimKategori Hakka = new ModelKuraniKerimKategori("Hâkka",69);
        ModelKuraniKerimKategori Mearic = new ModelKuraniKerimKategori("Meâric",70);
        ModelKuraniKerimKategori Nuh = new ModelKuraniKerimKategori("Nuh",71);
        ModelKuraniKerimKategori Cin = new ModelKuraniKerimKategori("Cin",72);
        ModelKuraniKerimKategori Muzzemmil = new ModelKuraniKerimKategori("Müzzemmil",73);
        ModelKuraniKerimKategori Muddesir = new ModelKuraniKerimKategori("Müddesir",74);
        ModelKuraniKerimKategori Kiyamet = new ModelKuraniKerimKategori("Kıyamet",75);
        ModelKuraniKerimKategori insan = new ModelKuraniKerimKategori("İnsan",76);
        ModelKuraniKerimKategori murselat = new ModelKuraniKerimKategori("Mürselât",77);
        ModelKuraniKerimKategori Nebe = new ModelKuraniKerimKategori("Nebe",78);
        ModelKuraniKerimKategori Naziat = new ModelKuraniKerimKategori("Nâziât",79);
        ModelKuraniKerimKategori Abese = new ModelKuraniKerimKategori("Abese",80);
        ModelKuraniKerimKategori Tekvir = new ModelKuraniKerimKategori("Tekvir",81);
        ModelKuraniKerimKategori intifar = new ModelKuraniKerimKategori("İnfitâr",82);
        ModelKuraniKerimKategori Mutaffifin = new ModelKuraniKerimKategori("Mutaffifin",83);
        ModelKuraniKerimKategori insikak = new ModelKuraniKerimKategori("İnşikak",84);
        ModelKuraniKerimKategori buruc = new ModelKuraniKerimKategori("Bürûc",85);
        ModelKuraniKerimKategori tarik = new ModelKuraniKerimKategori("Târık",86);
        ModelKuraniKerimKategori ala = new ModelKuraniKerimKategori("A'lâ",87);
        ModelKuraniKerimKategori gasiye = new ModelKuraniKerimKategori("Gâşiye",88);
        ModelKuraniKerimKategori Fecr = new ModelKuraniKerimKategori("Fecr",89);
        ModelKuraniKerimKategori Beled = new ModelKuraniKerimKategori("Beled",90);
        ModelKuraniKerimKategori sems = new ModelKuraniKerimKategori("Şems",91);
        ModelKuraniKerimKategori Leyl = new ModelKuraniKerimKategori("Leyl",92);
        ModelKuraniKerimKategori duha = new ModelKuraniKerimKategori("Duhâ",93);
        ModelKuraniKerimKategori insirah = new ModelKuraniKerimKategori("İnşirâh",94);
        ModelKuraniKerimKategori Tin = new ModelKuraniKerimKategori("Tin",95);
        ModelKuraniKerimKategori Alak = new ModelKuraniKerimKategori("Alak",96);
        ModelKuraniKerimKategori Kadir = new ModelKuraniKerimKategori("Kadir",97);
        ModelKuraniKerimKategori Beyyine = new ModelKuraniKerimKategori("Beyyine",98);
        ModelKuraniKerimKategori Zilzal = new ModelKuraniKerimKategori("Zilzâl",99);
        ModelKuraniKerimKategori adiyat = new ModelKuraniKerimKategori("Âdiyât",100);
        ModelKuraniKerimKategori karia = new ModelKuraniKerimKategori("Kâria",101);
        ModelKuraniKerimKategori tekasur = new ModelKuraniKerimKategori("Tekâsür",102);
        ModelKuraniKerimKategori Asr = new ModelKuraniKerimKategori("Asr",103);
        ModelKuraniKerimKategori Humeze = new ModelKuraniKerimKategori("Hümeze",104);
        ModelKuraniKerimKategori Fil = new ModelKuraniKerimKategori("Fil",105);
        ModelKuraniKerimKategori Kureys = new ModelKuraniKerimKategori("Kureyş",106);
        ModelKuraniKerimKategori Maun = new ModelKuraniKerimKategori("Mâûn",107);
        ModelKuraniKerimKategori Kevser = new ModelKuraniKerimKategori("Kevser",108);
        ModelKuraniKerimKategori kafirun = new ModelKuraniKerimKategori("Kâfirûn",109);
        ModelKuraniKerimKategori Nasr = new ModelKuraniKerimKategori("Nasr",110);
        ModelKuraniKerimKategori Tebbet = new ModelKuraniKerimKategori("Tebbet",111);
        ModelKuraniKerimKategori ihlas = new ModelKuraniKerimKategori("İhlâs",112);
        ModelKuraniKerimKategori felak = new ModelKuraniKerimKategori("Felâk",113);
        ModelKuraniKerimKategori nas = new ModelKuraniKerimKategori("Nâs",114);


        modelKuraniKerimKategoriArrayList.add(Fatiha);
        modelKuraniKerimKategoriArrayList.add(Bakara);
        modelKuraniKerimKategoriArrayList.add(aliimran);
        modelKuraniKerimKategoriArrayList.add(Nisa);
        modelKuraniKerimKategoriArrayList.add(maide);
        modelKuraniKerimKategoriArrayList.add(Enam);
        modelKuraniKerimKategoriArrayList.add(Araf);
        modelKuraniKerimKategoriArrayList.add(Enfal);
        modelKuraniKerimKategoriArrayList.add(Tevbe);
        modelKuraniKerimKategoriArrayList.add(Yunus);
        modelKuraniKerimKategoriArrayList.add(Hud);
        modelKuraniKerimKategoriArrayList.add(Yusuf);
        modelKuraniKerimKategoriArrayList.add(Rad);
        modelKuraniKerimKategoriArrayList.add(i̇brahim);
        modelKuraniKerimKategoriArrayList.add(Hicr);
        modelKuraniKerimKategoriArrayList.add(Nahl);
        modelKuraniKerimKategoriArrayList.add(isra);
        modelKuraniKerimKategoriArrayList.add(Kehf);
        modelKuraniKerimKategoriArrayList.add(Meryem);
        modelKuraniKerimKategoriArrayList.add(Taha);
        modelKuraniKerimKategoriArrayList.add(Enbiya);
        modelKuraniKerimKategoriArrayList.add(Hac);
        modelKuraniKerimKategoriArrayList.add(Muminun);
        modelKuraniKerimKategoriArrayList.add(Nur);
        modelKuraniKerimKategoriArrayList.add(Furkan);
        modelKuraniKerimKategoriArrayList.add(suara);
        modelKuraniKerimKategoriArrayList.add(Neml);
        modelKuraniKerimKategoriArrayList.add(Kasas);
        modelKuraniKerimKategoriArrayList.add(Ankebut);
        modelKuraniKerimKategoriArrayList.add(Rum);
        modelKuraniKerimKategoriArrayList.add(Lokman);
        modelKuraniKerimKategoriArrayList.add(Secde);
        modelKuraniKerimKategoriArrayList.add(Ahzab);
        modelKuraniKerimKategoriArrayList.add(Sebe);
        modelKuraniKerimKategoriArrayList.add(Fatir);
        modelKuraniKerimKategoriArrayList.add(Yasin);
        modelKuraniKerimKategoriArrayList.add(Saffat);
        modelKuraniKerimKategoriArrayList.add(sad);
        modelKuraniKerimKategoriArrayList.add(zumer);
        modelKuraniKerimKategoriArrayList.add(mumin);
        modelKuraniKerimKategoriArrayList.add(Fussilet);
        modelKuraniKerimKategoriArrayList.add(sura);
        modelKuraniKerimKategoriArrayList.add(Zuhruf);
        modelKuraniKerimKategoriArrayList.add(Duhan);
        modelKuraniKerimKategoriArrayList.add(Casiye);
        modelKuraniKerimKategoriArrayList.add(Ahkaf);
        modelKuraniKerimKategoriArrayList.add(Muhammed);
        modelKuraniKerimKategoriArrayList.add(Fetih);
        modelKuraniKerimKategoriArrayList.add(Hucurat);
        modelKuraniKerimKategoriArrayList.add(Kaf);
        modelKuraniKerimKategoriArrayList.add(Zariyat);
        modelKuraniKerimKategoriArrayList.add(Tur);
        modelKuraniKerimKategoriArrayList.add(Necm);
        modelKuraniKerimKategoriArrayList.add(Kamer);
        modelKuraniKerimKategoriArrayList.add(Rahman);
        modelKuraniKerimKategoriArrayList.add(Vakia);
        modelKuraniKerimKategoriArrayList.add(Hadid);
        modelKuraniKerimKategoriArrayList.add(Mucadele);
        modelKuraniKerimKategoriArrayList.add(Hasr);
        modelKuraniKerimKategoriArrayList.add(Mumtehine);
        modelKuraniKerimKategoriArrayList.add(Saf);
        modelKuraniKerimKategoriArrayList.add(Cuma);
        modelKuraniKerimKategoriArrayList.add(Munafikun);
        modelKuraniKerimKategoriArrayList.add(Tegabun);
        modelKuraniKerimKategoriArrayList.add(Talak);
        modelKuraniKerimKategoriArrayList.add(Tahrim);
        modelKuraniKerimKategoriArrayList.add(Mulk);
        modelKuraniKerimKategoriArrayList.add(Kalem);
        modelKuraniKerimKategoriArrayList.add(Hakka);
        modelKuraniKerimKategoriArrayList.add(Mearic);
        modelKuraniKerimKategoriArrayList.add(Nuh);
        modelKuraniKerimKategoriArrayList.add(Cin);
        modelKuraniKerimKategoriArrayList.add(Muzzemmil);
        modelKuraniKerimKategoriArrayList.add(Muddesir);
        modelKuraniKerimKategoriArrayList.add(Kiyamet);
        modelKuraniKerimKategoriArrayList.add(insan);
        modelKuraniKerimKategoriArrayList.add(murselat);
        modelKuraniKerimKategoriArrayList.add(Nebe);
        modelKuraniKerimKategoriArrayList.add(Naziat);
        modelKuraniKerimKategoriArrayList.add(Abese);
        modelKuraniKerimKategoriArrayList.add(Tekvir);
        modelKuraniKerimKategoriArrayList.add(intifar);
        modelKuraniKerimKategoriArrayList.add(Mutaffifin);
        modelKuraniKerimKategoriArrayList.add(insikak);
        modelKuraniKerimKategoriArrayList.add(buruc);
        modelKuraniKerimKategoriArrayList.add(tarik);
        modelKuraniKerimKategoriArrayList.add(ala);
        modelKuraniKerimKategoriArrayList.add(gasiye);
        modelKuraniKerimKategoriArrayList.add(Fecr);
        modelKuraniKerimKategoriArrayList.add(Beled);
        modelKuraniKerimKategoriArrayList.add(sems);
        modelKuraniKerimKategoriArrayList.add(Leyl);
        modelKuraniKerimKategoriArrayList.add(duha);
        modelKuraniKerimKategoriArrayList.add(insirah);
        modelKuraniKerimKategoriArrayList.add(Tin);
        modelKuraniKerimKategoriArrayList.add(Alak);
        modelKuraniKerimKategoriArrayList.add(Kadir);
        modelKuraniKerimKategoriArrayList.add(Beyyine);
        modelKuraniKerimKategoriArrayList.add(Zilzal);
        modelKuraniKerimKategoriArrayList.add(adiyat);
        modelKuraniKerimKategoriArrayList.add(karia);
        modelKuraniKerimKategoriArrayList.add(tekasur);
        modelKuraniKerimKategoriArrayList.add(Asr);
        modelKuraniKerimKategoriArrayList.add(Humeze);
        modelKuraniKerimKategoriArrayList.add(Fil);
        modelKuraniKerimKategoriArrayList.add(Kureys);
        modelKuraniKerimKategoriArrayList.add(Maun);
        modelKuraniKerimKategoriArrayList.add(Kevser);
        modelKuraniKerimKategoriArrayList.add(kafirun);
        modelKuraniKerimKategoriArrayList.add(Nasr);
        modelKuraniKerimKategoriArrayList.add(Tebbet);
        modelKuraniKerimKategoriArrayList.add(ihlas);
        modelKuraniKerimKategoriArrayList.add(felak);
        modelKuraniKerimKategoriArrayList.add(nas);



    }





}