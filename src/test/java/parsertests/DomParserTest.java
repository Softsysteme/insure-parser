package parsertests;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

import caches.InsureParserCacheManager;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.StandardFeldelementeigenschaftenStandard;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.StandardFeldelementeigenschaftenStandardBearbeitung;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.StandardFeldelementeigenschaftenStandardBeauskunftung;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.TemplateFeldelementeigenschaftenBestellungbeauskunften;
import de.adesso.ais.infoservicereference.feldsteuerung.feldelementeigenschaften.impl.TemplateFeldelementeigenschaftenModellvariantePolo;
import de.adesso.ais.infoservicereference.feldsteuerung.impl.FeldsteuerungBestellsystem;
import de.adesso.ais.infoservicereference.schnittstellensteuerung.impl.SchnittstellensteuerungBestellsystem;
import insure.domain.prototype.kontext.IKontext;
import insure.infoservice.feldsteuerung.Feldsteuerung;
import insure.infoservice.feldsteuerung.IEingabeelement;
import insure.infoservice.feldsteuerung.IEingabeelementeigenschaft;
import insure.infoservice.feldsteuerung.IFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.StandardFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.TemplateFeldelementeigenschaften;
import insure.infoservice.feldsteuerung.impl.FeldsteuerungStandard;
import insure.infoservice.schnittstellensteuerung.ISchnittstelle;
import insure.infoservice.schnittstellensteuerung.ISchnittstellenbelieferungsbedingung;
import insure.infoservice.schnittstellensteuerung.ISchnittstellenzuordnung;
import insure.infoservice.schnittstellensteuerung.Schnittstellensteuerung;
import insure.infoservice.schnittstellensteuerung.impl.SchnittstellensteuerungStandard;
import parser.DomParser;

public class DomParserTest {

    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;
    static DomParser dParser = new DomParser();
    static String[] paths = { "/insure-domain.insure", "/infoservice.insure", "/domain-reference.insure", "/infoservice-reference.insure" };
    static String[] ecorePaths = { "/domain.ecore", "/infoservice.ecore", "/domain-reference.ecore", "/infoservice-reference.ecore" };

    @BeforeClass
    public static void init() {
        for (int i = 0; i < paths.length; i++) {
            dParser.parseXml(paths[i], ecorePaths[i]);
        }
    }

    /*--------------------------------------------------------------------------------------------------------
    test if something was parsed and stored in the memory
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void domParserWorksCorrectly() {
        assert (dParser.getSpeicher().size() != 0);
        // dParser.printMemoryContentInDetails();
    }

    /*--------------------------------------------------------------------------------------------------------
    StandardFeldelementeigenschaften parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void StandardFeldelementEigenschaftenCorrectlyParsed() {

        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof StandardFeldelementeigenschaften) {
                assert (((StandardFeldelementeigenschaften) next).getStandardSteuerelementeigenschaft() != null
                        || ((StandardFeldelementeigenschaften) next).getStandardEingabeelementeigenschaft() != null);

            }

        }
    }

    @Test
    public void StandardFeldelementeigebschaftenStandardParsedCorrectly() {
        StandardFeldelementeigenschaftenStandard sf1 = new StandardFeldelementeigenschaftenStandard();
        StandardFeldelementeigenschaften sf2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof StandardFeldelementeigenschaften) {
                if (((StandardFeldelementeigenschaften) next).getModelElementId().contentEquals("_SuVeKSbaEeWDoMiQXiXZDQ")) {
                    sf2 = (StandardFeldelementeigenschaften) next;
                }
            }

        }
        assert (sf2 != null);
        assert (sf1.getName().contentEquals(sf2.getName()));
        assert (sf1.getStandardEingabeelementeigenschaft().name().contentEquals(sf2.getStandardEingabeelementeigenschaft().name()));
        assert (sf1.getStandardSteuerelementeigenschaft().name().contentEquals(sf2.getStandardSteuerelementeigenschaft().name()));

    }

    @Test
    public void StandardFeldelementeigenschaftenStandardBearbeitungParsedCorrectly() {
        StandardFeldelementeigenschaftenStandardBearbeitung sf1 = new StandardFeldelementeigenschaftenStandardBearbeitung();
        StandardFeldelementeigenschaften sf2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof StandardFeldelementeigenschaften) {
                if (((StandardFeldelementeigenschaften) next).getModelElementId().contentEquals("_p2lZvqQBEeWbN4B32j-AcQ")) {
                    sf2 = (StandardFeldelementeigenschaften) next;
                }
            }

        }
        assert (sf2 != null);
        assert (sf1.getName().contentEquals(sf2.getName()));
        assert (sf1.getStandardEingabeelementeigenschaft().name().contentEquals(sf2.getStandardEingabeelementeigenschaft().name()));
        assert (sf1.getStandardSteuerelementeigenschaft().name().contentEquals(sf2.getStandardSteuerelementeigenschaft().name()));
    }

    @Test
    public void StandardFeldelementeigenschaftenStandardBeauskunftungParsedCorrectly() {
        StandardFeldelementeigenschaftenStandardBeauskunftung sf1 = new StandardFeldelementeigenschaftenStandardBeauskunftung();
        StandardFeldelementeigenschaften sf2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof StandardFeldelementeigenschaften) {
                if (((StandardFeldelementeigenschaften) next).getModelElementId().contentEquals("_yqcAM6SWEeWkAL6LixI-yg")) {
                    sf2 = (StandardFeldelementeigenschaften) next;
                }
            }

        }
        assert (sf2 != null);
        assert (sf1.getName().contentEquals(sf2.getName()));
        assert (sf1.getStandardEingabeelementeigenschaft().name().contentEquals(sf2.getStandardEingabeelementeigenschaft().name()));
        assert (sf1.getStandardSteuerelementeigenschaft().name().contentEquals(sf2.getStandardSteuerelementeigenschaft().name()));
    }

    /*--------------------------------------------------------------------------------------------------------
    TemplateFeldelementeigenschaften parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void TemplateFeldelementeigenschaftenAusstattungsvariantewaehlenParsedCorrectly() {
        TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen tpl1 = new TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen();
        TemplateFeldelementeigenschaften tpl2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof TemplateFeldelementeigenschaften) {
                if (((TemplateFeldelementeigenschaften) next).getModelElementId().contentEquals("_zt5SyibeEeWDoMiQXiXZDQ")) {
                    tpl2 = (TemplateFeldelementeigenschaften) next;
                }
            }

        }
        assert (tpl2 != null);
        assert (tpl1.getName().contentEquals(tpl2.getName()));
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Map.Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            assert (tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name().contentEquals(tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name()));
        }
        assert (tpl1.getTemplate().getModelElementId().contentEquals(tpl1.getTemplate().getModelElementId()));
    }

    @Test
    public void TemplateFeldelementeigenschaftenBestellungbeauskunftenParsedCorrectly() {
        TemplateFeldelementeigenschaftenBestellungbeauskunften tpl1 = new TemplateFeldelementeigenschaftenBestellungbeauskunften();
        TemplateFeldelementeigenschaften tpl2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof TemplateFeldelementeigenschaften) {
                if (((TemplateFeldelementeigenschaften) next).getModelElementId().contentEquals("_YQ8un6SXEeWkAL6LixI-yg")) {
                    tpl2 = (TemplateFeldelementeigenschaften) next;
                }
            }

        }
        assert (tpl2 != null);
        assert (tpl1.getName().contentEquals(tpl2.getName()));
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Map.Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            assert (tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name().contentEquals(tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name()));
        }
        assert (tpl1.getTemplate().getModelElementId().contentEquals(tpl1.getTemplate().getModelElementId()));
    }

    @Test
    public void TemplateFeldelementeigenschaftenModellvariantePoloParsedCorrectly() {
        TemplateFeldelementeigenschaftenModellvariantePolo tpl1 = new TemplateFeldelementeigenschaftenModellvariantePolo();
        TemplateFeldelementeigenschaften tpl2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof TemplateFeldelementeigenschaften) {
                if (((TemplateFeldelementeigenschaften) next).getModelElementId().contentEquals("_3zieCbVXEeWaM_Gy2tpIdw")) {
                    tpl2 = (TemplateFeldelementeigenschaften) next;
                }
            }

        }
        assert (tpl2 != null);
        assert (tpl1.getName().contentEquals(tpl2.getName()));
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Map.Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            assert (tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name().contentEquals(tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name()));
        }
        assert (tpl1.getTemplate().getModelElementId().contentEquals(tpl1.getTemplate().getModelElementId()));
    }

    /*--------------------------------------------------------------------------------------------------------
    Feldsteuerung parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void FeldsteuerungBestellsystemParsedCorrectly() {

        FeldsteuerungBestellsystem fldst1 = new FeldsteuerungBestellsystem();
        Feldsteuerung fldst2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof Feldsteuerung) {
                if (((Feldsteuerung) next).getName().contentEquals("Bestellsystem")) {
                    fldst2 = (Feldsteuerung) next;
                }
            }

        }
        assert (fldst2 != null);
        assert (fldst1.getName().contentEquals(fldst2.getName()));
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst1.getFeldelementeigenschaften().entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        System.out.println("------------------------------------");
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst2.getFeldelementeigenschaften().entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        System.out.println("------------------------------------");
        // assert (fldst1.getFeldelementeigenschaften().size() == fldst2.getFeldelementeigenschaften().size());
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst1.getFeldelementeigenschaften().entrySet()) {
            assert (fldst1.getFeldelementeigenschaften().get(entry.getKey()).getModelElementId().contentEquals(fldst2.getFeldelementeigenschaften().get(entry.getKey()).getModelElementId()));
        }
        assert (fldst1.getIdentifier().name().contentEquals(fldst2.getIdentifier().name()));
    }

    @Test
    public void FeldsteuerungStandardParsedCorrectly() {

        FeldsteuerungStandard fldst1 = new FeldsteuerungStandard();
        Feldsteuerung fldst2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof Feldsteuerung) {
                if (((Feldsteuerung) next).getModelElementId().contentEquals("_FONP99OqEeSVoOolBb6ZYQ")) {
                    fldst2 = (Feldsteuerung) next;
                }
            }

        }
        assert (fldst2 != null);
        assert (fldst1.getName().contentEquals(fldst2.getName()));
        assert (fldst1.getFeldelementeigenschaften().size() == fldst2.getFeldelementeigenschaften().size());
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst1.getFeldelementeigenschaften().entrySet()) {
            assert (fldst1.getFeldelementeigenschaften().get(entry.getKey()).getModelElementId().contentEquals(fldst2.getFeldelementeigenschaften().get(entry.getKey()).getModelElementId()));
        }
        assert (fldst1.basicGetIdentifier().name().contentEquals(fldst2.basicGetIdentifier().name()));
    }

    /*--------------------------------------------------------------------------------------------------------
    Schnittstelllensteuerung parsing test
    ---------------------------------------------------------------------------------------------------------* */
    // @Test
    public void SchnittstellensteuerungBestellsystemParsedCorrectly() {
        SchnittstellensteuerungBestellsystem shn1 = new SchnittstellensteuerungBestellsystem();
        Schnittstellensteuerung shn2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof Schnittstellensteuerung) {
                if (((Schnittstellensteuerung) next).getModelElementId().contentEquals("_uXfMY9OoEeSVoOolBb6ZYQ")) {
                    shn2 = (Schnittstellensteuerung) next;
                }
            }

        }
        assert (shn2 != null);
        assert (shn1.getName().contentEquals(shn2.getName()));
        System.out.println(shn1.getSchnittstellenversorgungen().size() + "," + shn2.getSchnittstellenversorgungen().size());
        assert (shn1.getSchnittstellenversorgungen().size() == shn2.getSchnittstellenversorgungen().size());
        for (

        Map.Entry<IKontext, ISchnittstellenzuordnung> entry : shn1.getSchnittstellenversorgungen().entrySet()) {
            assert (shn1.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId().contentEquals(shn2.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId()));
        }
        for (Entry<ISchnittstelle, ISchnittstellenbelieferungsbedingung<?, ?>> entry : shn1.getBelieferungsbedingungen().entrySet()) {
            assert (shn1.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId().contentEquals(shn2.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId()));
        }
        assert (shn1.getIdentifier().name().contentEquals(shn2.getIdentifier().name()));
    }

    @Test
    public void SchnittstellensteuerungStandardParsedCorrectly() {
        SchnittstellensteuerungStandard shn1 = new SchnittstellensteuerungStandard();
        Schnittstellensteuerung shn2 = null;
        Iterator<Object> iter = dParser.getSpeicher().iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof Schnittstellensteuerung) {
                if (((Schnittstellensteuerung) next).getModelElementId().contentEquals("_bCozoSbWEeWomoMrx8bFIQ")) {
                    shn2 = (Schnittstellensteuerung) next;
                }
            }

        }
        assert (shn2 != null);
        assert (shn1.getName().contentEquals(shn2.getName()));
        assert (shn1.getSchnittstellenversorgungen().size() == shn2.getSchnittstellenversorgungen().size());
        for (

        Map.Entry<IKontext, ISchnittstellenzuordnung> entry : shn1.getSchnittstellenversorgungen().entrySet()) {
            assert (shn1.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId().contentEquals(shn2.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId()));
        }
        for (Entry<ISchnittstelle, ISchnittstellenbelieferungsbedingung<?, ?>> entry : shn1.getBelieferungsbedingungen().entrySet()) {
            assert (shn1.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId().contentEquals(shn2.getSchnittstellenversorgungen().get(entry.getKey()).getModelElementId()));
        }
        assert (shn1.basicGetIdentifier().name().contentEquals(shn2.basicGetIdentifier().name()));
    }
}
