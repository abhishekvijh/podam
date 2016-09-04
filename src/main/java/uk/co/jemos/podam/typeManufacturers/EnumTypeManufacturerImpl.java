package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.Map;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

/**
 * Default Enum type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class EnumTypeManufacturerImpl extends AbstractTypeManufacturer<Enum<?>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Enum<?> getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Class<?> realAttributeType = attributeMetadata.getAttributeType();

        Enum<?> retValue = null;

        // Enum type
        int enumConstantsLength = realAttributeType.getEnumConstants().length;

        if (enumConstantsLength > 0) {
            int enumIndex = strategy.getIntegerInRange(0,
                    enumConstantsLength, attributeMetadata)
                    % enumConstantsLength;
            retValue = (Enum<?>) realAttributeType.getEnumConstants()[enumIndex];
        }

        return retValue;
    }
}