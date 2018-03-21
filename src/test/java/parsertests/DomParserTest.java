package parsertests;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import caches.InsureParserCacheManager;
import de.adesso.ais.domainreference.tickets.insure754.IInsure754TableContent;
import de.adesso.ais.domainreference.tickets.insure754.IInsure754TableRow;
import de.adesso.ais.domainreference.tickets.insure754.Insure754Table;
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
import tools.JavaPackageNameResolver;

public class DomParserTest {

    InsureParserCacheManager cm = InsureParserCacheManager.INSTANCE;

    static String paths = "/infoservice-reference.insure";
    static DomParser dParser;
    static String[] ecorePaths = { "/domain.ecore", "/infoservice.ecore", "/domain-reference.ecore", "/infoservice-reference.ecore" };

    @BeforeClass
    public static void init() {
        new JavaPackageNameResolver(ecorePaths);
        dParser = new DomParser(paths);
        dParser.parseFileAndReferencedFiles();
    }

    /*--------------------------------------------------------------------------------------------------------
    test if something was parsed and stored in the memory
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void domParserWorksCorrectly() {
        Assert.assertNotEquals(dParser.getSpeicher().size(), 0);
        // dParser.printMemoryContentInDetails();
    }

    /*--------------------------------------------------------------------------------------------------------
    StandardFeldelementeigenschaften parsing test
    ---------------------------------------------------------------------------------------------------------* */

    @Test
    public void StandardFeldelementeigebschaftenStandardParsedCorrectly() {
        StandardFeldelementeigenschaftenStandard sf1 = new StandardFeldelementeigenschaftenStandard();
        StandardFeldelementeigenschaften sf2 = (StandardFeldelementeigenschaften) dParser.getSpeicher().get("_SuVeKSbaEeWDoMiQXiXZDQ");
        Assert.assertNotNull(sf2);
        Assert.assertEquals(sf1.getName(), sf2.getName());
        Assert.assertEquals(sf1.getStandardEingabeelementeigenschaft().name(), sf2.getStandardEingabeelementeigenschaft().name());
        Assert.assertEquals(sf1.getStandardSteuerelementeigenschaft().name(), sf2.getStandardSteuerelementeigenschaft().name());

    }

    @Test
    public void StandardFeldelementeigenschaftenStandardBearbeitungParsedCorrectly() {
        StandardFeldelementeigenschaftenStandardBearbeitung sf1 = new StandardFeldelementeigenschaftenStandardBearbeitung();
        StandardFeldelementeigenschaften sf2 = (StandardFeldelementeigenschaften) dParser.getSpeicher().get("_p2lZvqQBEeWbN4B32j-AcQ");
        Assert.assertNotNull(sf2);
        Assert.assertEquals(sf1.getName(), sf2.getName());
        Assert.assertEquals(sf1.getStandardEingabeelementeigenschaft().name(), sf2.getStandardEingabeelementeigenschaft().name());
        Assert.assertEquals(sf1.getStandardSteuerelementeigenschaft().name(), sf2.getStandardSteuerelementeigenschaft().name());

    }

    @Test
    public void StandardFeldelementeigenschaftenStandardBeauskunftungParsedCorrectly() {
        StandardFeldelementeigenschaftenStandardBeauskunftung sf1 = new StandardFeldelementeigenschaftenStandardBeauskunftung();
        StandardFeldelementeigenschaften sf2 = (StandardFeldelementeigenschaften) dParser.getSpeicher().get("_yqcAM6SWEeWkAL6LixI-yg");
        Assert.assertNotNull(sf2);
        Assert.assertEquals(sf1.getName(), sf2.getName());
        Assert.assertEquals(sf1.getStandardEingabeelementeigenschaft().name(), sf2.getStandardEingabeelementeigenschaft().name());
        Assert.assertEquals(sf1.getStandardSteuerelementeigenschaft().name(), sf2.getStandardSteuerelementeigenschaft().name());

    }

    /*--------------------------------------------------------------------------------------------------------
    TemplateFeldelementeigenschaften parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void TemplateFeldelementeigenschaftenAusstattungsvariantewaehlenParsedCorrectly() {
        TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen tpl1 = new TemplateFeldelementeigenschaftenAusstattungsvariantewaehlen();
        TemplateFeldelementeigenschaften tpl2 = null;
        tpl2 = (TemplateFeldelementeigenschaften) dParser.getSpeicher().get("_zt5SyibeEeWDoMiQXiXZDQ");
        Assert.assertNotNull(tpl2);
        Assert.assertEquals(tpl1.getName(), tpl2.getName());
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            Assert.assertEquals(tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name(), tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name());
        }
        Assert.assertNotNull(tpl2.getTemplate());
    }

    @Test
    public void TemplateFeldelementeigenschaftenBestellungbeauskunftenParsedCorrectly() {
        TemplateFeldelementeigenschaftenBestellungbeauskunften tpl1 = new TemplateFeldelementeigenschaftenBestellungbeauskunften();
        TemplateFeldelementeigenschaften tpl2 = (TemplateFeldelementeigenschaften) dParser.getSpeicher().get("_YQ8un6SXEeWkAL6LixI-yg");
        Assert.assertNotNull(tpl2);
        Assert.assertEquals(tpl1.getName(), tpl2.getName());
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            Assert.assertEquals(tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name(), tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name());
        }
        Assert.assertNotNull(tpl2.getTemplate());
    }

    @Test
    public void TemplateFeldelementeigenschaftenModellvariantePoloParsedCorrectly() {
        TemplateFeldelementeigenschaftenModellvariantePolo tpl1 = new TemplateFeldelementeigenschaftenModellvariantePolo();
        TemplateFeldelementeigenschaften tpl2 = (TemplateFeldelementeigenschaften) dParser.getSpeicher().get("_3zieCbVXEeWaM_Gy2tpIdw");
        Assert.assertNotNull(tpl2);
        Assert.assertEquals(tpl1.getName(), tpl2.getName());
        assert (tpl1.getEingabeelementeigenschaften().size() == tpl2.getEingabeelementeigenschaften().size());
        for (Entry<IEingabeelement, IEingabeelementeigenschaft> entry : tpl1.getEingabeelementeigenschaften().entrySet()) {
            Assert.assertEquals(tpl1.getEingabeelementeigenschaften().get(entry.getKey()).name(), tpl2.getEingabeelementeigenschaften().get(entry.getKey()).name());
        }
        Assert.assertNotNull(tpl2.getTemplate());
    }

    /*--------------------------------------------------------------------------------------------------------
    Feldsteuerung parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void FeldsteuerungBestellsystemParsedCorrectly() {

        FeldsteuerungBestellsystem fldst1 = new FeldsteuerungBestellsystem();
        Feldsteuerung fldst2 = (Feldsteuerung) dParser.getSpeicher().get("_KXNjOBsREeWeoYGlWqgNWQ");
        Assert.assertNotNull(fldst2);
        Assert.assertEquals(fldst2.getName(), fldst1.getName());
        Assert.assertEquals(fldst1.getFeldelementeigenschaften().size(), fldst2.getFeldelementeigenschaften().size());
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst1.getFeldelementeigenschaften().entrySet()) {
            // Assert.assertNotNull(fldst2.getFeldelementeigenschaften().get(entry.getKey()));
        }
        Assert.assertEquals(fldst1.getIdentifier().name(), (fldst2.getIdentifier().name()));
    }

    @Test
    public void FeldsteuerungStandardParsedCorrectly() {

        FeldsteuerungStandard fldst1 = new FeldsteuerungStandard();
        Feldsteuerung fldst2 = (Feldsteuerung) dParser.getSpeicher().get("_FONP99OqEeSVoOolBb6ZYQ");
        Assert.assertNotNull(fldst2);
        Assert.assertEquals(fldst2.getName(), fldst1.getName());
        assert (fldst1.getFeldelementeigenschaften().size() == fldst2.getFeldelementeigenschaften().size());
        for (Entry<IKontext, IFeldelementeigenschaften> entry : fldst1.getFeldelementeigenschaften().entrySet()) {
            Assert.assertEquals(fldst1.getFeldelementeigenschaften().get(entry.getKey()), (fldst2.getFeldelementeigenschaften().get(entry.getKey())));
        }

        Assert.assertEquals(fldst1.getIdentifier().name(), (fldst2.getIdentifier().name()));

    }

    /*--------------------------------------------------------------------------------------------------------
    Schnittstelllensteuerung parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @Test
    public void SchnittstellensteuerungBestellsystemParsedCorrectly() {
        SchnittstellensteuerungBestellsystem shn1 = new SchnittstellensteuerungBestellsystem();
        Schnittstellensteuerung shn2 = (Schnittstellensteuerung) dParser.getSpeicher().get("_uXfMY9OoEeSVoOolBb6ZYQ");

        Assert.assertNotNull(shn2);
        Assert.assertEquals(shn1.getName(), shn2.getName());
        Assert.assertEquals(shn1.getSchnittstellenversorgungen().size(), shn2.getSchnittstellenversorgungen().size());
        for (Map.Entry<IKontext, ISchnittstellenzuordnung> entry : shn1.getSchnittstellenversorgungen().entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
            // Assert.assertEquals(shn1.getSchnittstellenversorgungen().get(entry.getKey()), shn2.getSchnittstellenversorgungen().get(entry.getKey()));
        }
        System.out.println(shn1.getBelieferungsbedingungen().toString());
        Assert.assertEquals(shn1.getIdentifier().name(), shn2.getIdentifier().name());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void SchnittstellensteuerungStandardParsedCorrectly() {
        SchnittstellensteuerungStandard shn1 = new SchnittstellensteuerungStandard();
        Schnittstellensteuerung shn2 = (Schnittstellensteuerung) dParser.getSpeicher().get("_bCozoSbWEeWomoMrx8bFIQ");

        Assert.assertNotNull(shn2);
        Assert.assertEquals(shn1.getName(), shn2.getName());
        Assert.assertEquals(shn1.getSchnittstellenversorgungen().size(), shn2.getSchnittstellenversorgungen().size());
        for (Map.Entry<IKontext, ISchnittstellenzuordnung> entry : shn1.getSchnittstellenversorgungen().entrySet()) {
            Assert.assertEquals(shn1.getSchnittstellenversorgungen().get(entry.getKey()), shn2.getSchnittstellenversorgungen().get(entry.getKey()));
        }
        Assert.assertEquals(shn1.getBelieferungsbedingungen().size(), shn2.getBelieferungsbedingungen().size());
        for (Entry<ISchnittstelle, ISchnittstellenbelieferungsbedingung<?, ?>> entry : shn1.getBelieferungsbedingungen().entrySet()) {
            Assert.assertEquals(shn1.getSchnittstellenversorgungen().get(entry.getKey()), shn2.getSchnittstellenversorgungen().get(entry.getKey()));
        }
        Assert.assertEquals(shn1.getIdentifier(), shn2.getIdentifier());
    }

    /*--------------------------------------------------------------------------------------------------------
    Functions parsing test
    ---------------------------------------------------------------------------------------------------------* */
    @SuppressWarnings("unchecked")
    @Test
    public void Insure450CalculatorEinCalculatorParsedCorrectly() {
        // Map<String, Object> testContainment = new HashMap<String, Object>();
        // Insure450CalculatorEinCalculator einCalculator = new Insure450CalculatorEinCalculator();
        // Object function = null;
        // Iterator<Object> iter = dParser.getSpeicher().iterator();
        // while (iter.hasNext()) {
        // Object next = iter.next();
        // if (next instanceof IFunction) {
        // if (((IFunction) next).getName().contentEquals("EinCalculator")) {
        // function = (IFunction) next;
        // }
        // }
        //
        // }
        // assert (function != null);
        // Field field = null;
        //
        // try {
        // field = function.getClass().getField("containments");
        // } catch (NoSuchFieldException | SecurityException e) {
        // // TODO Auto-generated catch block
        //
        // }
        // field.setAccessible(true);
        // try {
        // testContainment.putAll((Map<String, Object>) field.get(function));
        // // testContainment.put((String) function.getClass().getField("modelElementId").get(function), function);
        //
        // } catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
        // // TODO Auto-generated catch block
        // }
        // boolean weiter = true;
        // while (weiter) {
        // Iterator<Entry<String, Object>> it = testContainment.entrySet().iterator();
        // while (it.hasNext()) {
        // Entry<String, Object> entry = it.next();
        // for (Field f : entry.getValue().getClass().getDeclaredFields()) {
        // f.setAccessible(true);
        // Object object = null;
        // try {
        // object = f.get(entry.getValue());
        // } catch (IllegalArgumentException | IllegalAccessException e) {
        // // TODO Auto-generated catch block
        // }
        // if (object != null) {
        // for (Field fd : dParser.getAllDeclaredFields(object.getClass())) {
        // if (fd.getName().contentEquals("modelElementId")) {
        // fd.setAccessible(true);
        // try {
        // weiter = (testContainment.putIfAbsent((String) fd.get(object), object)) != null;
        // } catch (IllegalArgumentException | IllegalAccessException e) {
        // // TODO Auto-generated catch block
        // }
        //
        // }
        // }
        //
        // }
        // }
        // }
        // }
        // for (Entry<String, Object> entry : testContainment.entrySet()) {
        // System.out.println(entry.getKey() + "," + entry.getValue());
        // }
        // System.out.println(cm.retrieveFromCache("_sRGtUOOqEeavgqX1epbq9g"));
        int i = 0;
        for (String key : cm.getKeys()) {
            if (key.contentEquals("_yObRoL-3EeeW0_8SxuqYYg")) {
                Insure754Table object;
                object = (Insure754Table) cm.retrieveFromCache(key);
                // System.out.println(object.getName() + " ," + object.getRows().size());
                for (IInsure754TableRow inst : object.getRows()) {
                    for (IInsure754TableContent content : inst.getContent()) {
                        // System.out.println(content.toString());
                        if (content.getFurtherContent() != null) {
                            // System.out.println("further content: " + content.getFurtherContent().toString());
                        }
                    }
                    // System.out.println(inst.toString());
                }
            }
            // System.out.println(key + "(" + i++ + ")" + " ," + cm.retrieveFromCache(key).toString());
        }
    }

}
