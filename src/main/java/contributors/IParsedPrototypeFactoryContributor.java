package contributors;

import java.util.Map;

import insure.core.IIdentifiablePrototype;
import insure.core.IPrototypeIdentifier;
import insure.core.factory.enums.IPrototypeFactoryContributor;

public interface IParsedPrototypeFactoryContributor extends IPrototypeFactoryContributor {

    Map<IPrototypeIdentifier, ? extends IIdentifiablePrototype<? extends IPrototypeIdentifier>> getParsedIdentifiablePrototypes();
}
