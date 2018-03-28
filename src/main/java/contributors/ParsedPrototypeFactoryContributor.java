package contributors;

import java.util.HashMap;
import java.util.Map;

import insure.core.IIdentifiablePrototype;
import insure.core.IPrototypeIdentifier;
import parser.DomParser;
import tools.JavaPackageNameResolver;

public class ParsedPrototypeFactoryContributor implements IParsedPrototypeFactoryContributor {
    private DomParser dParser;
    private String[] ecorePaths = { "/domain.ecore", "/infoservice.ecore", "/domain-reference.ecore", "/infoservice-reference.ecore" };
    private Map<IPrototypeIdentifier, IIdentifiablePrototype<IPrototypeIdentifier>> identifiablePrototypes;

    public ParsedPrototypeFactoryContributor(String filePath) {
        dParser = new DomParser(filePath);
        identifiablePrototypes = new HashMap<IPrototypeIdentifier, IIdentifiablePrototype<IPrototypeIdentifier>>();
        init();
    }

    public void init() {
        new JavaPackageNameResolver(ecorePaths);
        dParser.parseFileAndReferencedFiles();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<IPrototypeIdentifier, IIdentifiablePrototype<IPrototypeIdentifier>> getParsedIdentifiablePrototypes() {
        for (Map.Entry<String, Object> entry : dParser.getSpeicher().entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                if (value instanceof IIdentifiablePrototype<?>) {
                    identifiablePrototypes.put(((IIdentifiablePrototype<IPrototypeIdentifier>) value).getIdentifier(), (IIdentifiablePrototype<IPrototypeIdentifier>) value);
                }
            }
        }
        return identifiablePrototypes;

    }

    @Override
    public Map<IPrototypeIdentifier, Class<? extends IIdentifiablePrototype<? extends IPrototypeIdentifier>>> getIdentifiablePrototypes() {
        // TODO Auto-generated method stub
        return null;
    }
}
