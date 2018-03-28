package factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import contributors.ParsedPrototypeFactoryContributor;
import insure.core.IIdentifiablePrototype;
import insure.core.IPrototypeIdentifier;
import insure.core.factory.enums.IPrototypeFactory;

public class ParsedPrototypeFactory implements IPrototypeFactory {

    public static final Logger LOGGER = LoggerFactory.getLogger(ParsedPrototypeFactory.class);

    private final Map<IPrototypeIdentifier, IIdentifiablePrototype<IPrototypeIdentifier>> prototypes =
            new ConcurrentHashMap<IPrototypeIdentifier, IIdentifiablePrototype<IPrototypeIdentifier>>();

    public ParsedPrototypeFactory(String filePath) {
        initializeIdentifiablePrototypes(filePath);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <I extends IPrototypeIdentifier, T extends IIdentifiablePrototype<I>> T getIdentifiablePrototype(I identifier) {
        return (T) prototypes.get(identifier);

    }

    private void initializeIdentifiablePrototypes(String filePath) {
        ParsedPrototypeFactoryContributor contributor = new ParsedPrototypeFactoryContributor(filePath);
        prototypes.putAll(contributor.getParsedIdentifiablePrototypes());
    }

    public Map<IPrototypeIdentifier, IIdentifiablePrototype<IPrototypeIdentifier>> getIdentifiablePrototypeMap() {
        return prototypes;
    }

}
