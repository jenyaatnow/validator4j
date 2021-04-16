package io.github.jenyaatnow.validator4j.apt;


import io.github.jenyaatnow.validator4j.codegen.DataType;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.List;

class TypeUtilsTest {

    private TypeUtils testable;

    private Types types;
    private Elements elements;

    private TypeMirror typeMirror;
    private Element element;

    @BeforeEach
    void setUp() {
        types = Mockito.mock(Types.class);
        elements = Mockito.mock(Elements.class);

        final var processingEnv = Mockito.mock(ProcessingEnvironment.class);
        Mockito.when(processingEnv.getTypeUtils()).thenReturn(types);
        Mockito.when(processingEnv.getElementUtils()).thenReturn(elements);

        testable = Mockito.spy(new TypeUtils(processingEnv));

        typeMirror = Mockito.mock(DeclaredType.class);
        element = Mockito.mock(Element.class);
        Mockito.when(types.asElement(typeMirror)).thenReturn(element);
    }

    @Test
    void getEnumDataType() {
        Mockito.when(element.getKind()).thenReturn(ElementKind.ENUM);

        final var actual = testable.getDataType(typeMirror);

        Assertions.assertEquals(DataType.SCALAR, actual);
    }

    @Test
    void getScalarCollectionDataType() {
        final var typeDescriptor = Mockito
            .when(Mockito.mock(TypeDescriptor.class).getDataType())
            .thenReturn(DataType.SCALAR)
            .<TypeDescriptor>getMock();

        final var typeParam = Mockito.mock(DeclaredType.class);

        Mockito.doReturn(List.of(typeParam)).when((DeclaredType) typeMirror).getTypeArguments();
        Mockito.doReturn(typeDescriptor).when(testable).getTypeDescriptor(typeParam);
        Mockito.doReturn(true).when(testable).isAssignable(typeMirror, DataType.COLLECTION);

        final var actual = testable.getDataType(typeMirror);

        Assertions.assertEquals(DataType.COLLECTION, actual);
    }

    @Test
    void getValidatableCollectionDataType() {
        final var typeDescriptor = Mockito
            .when(Mockito.mock(TypeDescriptor.class).getDataType())
            .thenReturn(DataType.VALIDATABLE)
            .<TypeDescriptor>getMock();

        final var typeParam = Mockito.mock(DeclaredType.class);

        Mockito.doReturn(List.of(typeParam)).when((DeclaredType) typeMirror).getTypeArguments();
        Mockito.doReturn(typeDescriptor).when(testable).getTypeDescriptor(typeParam);
        Mockito.doReturn(true).when(testable).isAssignable(typeMirror, DataType.V_COLLECTION);

        final var actual = testable.getDataType(typeMirror);

        Assertions.assertEquals(DataType.V_COLLECTION, actual);
    }
}
