package com.example.annotationprocessorlib.annotationProcessor;

import com.example.annotationlib2.annotation.GenerateInterface;
import com.example.annotationprocessorlib.utils.Logger;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.annotationlib2.annotation.GenerateInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CreateInterfaceProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;
    Logger logger;

    private int r = 1;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
        logger = new Logger(processingEnv.getMessager());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        logger.info("this.getclass() : " + this.getClass().toString() + "\n");
        this.getClass().getSuperclass();
        logger.info("fthis.getClass().getSuperclass() : " + this.getClass().getSuperclass().toString() + "\n");

        for (TypeElement e : set) {
            logger.info("first params element e is: " + e.toString() + "\n");
        }
        messager.printMessage(Diagnostic.Kind.NOTE, "process() is excute ...");
        logger.info("process() is excute ...");
        Set<? extends Element> rootElements = roundEnvironment.getRootElements();
        logger.info("输入的所有类有：");
        for (Element e : rootElements) {
            //(">>>" + e.getSimpleName());
            logger.info(">>>" + e.getSimpleName());
        }
        logger.info("需要生成接口的类有：");
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(GenerateInterface.class);
        for (Element e : elementsAnnotatedWith) {
            logger.info(">>>" + e.getSimpleName());
            GenerateInterface annotation = e.getAnnotation(GenerateInterface.class);
            String className = e.getSimpleName() + annotation.suffix();
            String classString = "package com.example.bean;\n"
                    +"public interface "+className+" {\n";
            //获取所有的方法元素
            List<? extends Element> genElementAlls = e.getEnclosedElements();
            logger.info(">>>> 类"+e.getSimpleName()+"封装元素(仅对修饰符有public的生成接口方法):");
            for(Element e1 : genElementAlls){
                logger.info(">>> >>> "+e1.getSimpleName()+" 修饰符："+e1.getModifiers());
                if(!e1.getSimpleName().toString().equals("<init>") && e1.asType() instanceof ExecutableType && isPublic(e1)){
                    logger.info(">>> >>> >>> "+e1.getSimpleName());
                    classString += " void "+e1.getSimpleName()+"();\n";
                }
            }
            classString+="}";
            try {
                JavaFileObject jfo = filer.createSourceFile("com.example.bean."+className, e);
                Writer writer = jfo.openWriter();
                writer.flush();
                writer.append(classString);
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        logger.info("-------------------注解处理器第" + (r++) + "次循环处理结束...\n");
        return false;
    }

    //判断元素是否为public
    public boolean isPublic(Element e){
        //获取元素的修饰符Modifier,注意此处的Modifier
        //非java.lang.reflect.Modifier
        Set<Modifier> modifiers = e.getModifiers();
        for(Modifier m: modifiers){
            if(m.equals(Modifier.PUBLIC)) return true;
        }
        return false;
    }
}
