package me.qfdk.esir.wh.tests

import com.google.inject.Inject
import me.qfdk.esir.wh.WhInjectorProvider
import me.qfdk.esir.wh.wh.wh
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.InMemoryFileSystemAccess
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(WhInjectorProvider))
class PrettyPrinterTest {
    @Inject IGenerator underTest
    @Inject IGenerator underTestPrettyPrint
    @Inject ParseHelper<wh> parseHelper 
     
    @Test
    def testDefinition() {
        val model = parseHelper.parse('''
       	function bonjour : read X,Y,Z,E%nop%write Z
        ''')
        val fsa = new InMemoryFileSystemAccess()
        underTest.doGenerate(model.eResource, fsa)
        println(fsa.files)
        assertEquals(
            '''
            function bonjour: 
            read X, Y, Z, E
            %
            	nop
            %
            write Z
            
            '''.toString, fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString)
    
        assertTrue(fsa.files.containsKey(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp"))
    	assertTrue(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains("function "))
    	assertTrue(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains(": \nread "))
    	assertTrue(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains("read "))
    	assertTrue(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains("write "))
    	assertTrue(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains("%"))    	
    	assertFalse(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains(" write "))
    	assertFalse(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains(" read "))
    	assertFalse(fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString.contains("% "))
    }
    
    @Inject
    private JavaIoFileSystemAccess fileAccess;
    
	@Test
	def testDoublePrettyPrinter() {
       	
       	fileAccess.setOutputPath(".");
       
        val model = parseHelper.parse(fileAccess.readTextFile("test.wh").toString());
        val fsa = new InMemoryFileSystemAccess()
        underTest.doGenerate(model.eResource, fsa)
        //val model = parseHelper.parse(fileAccess.readTextFile("sth.wh").toString())
        println(fsa.files+"\n");
        val parserPrettyPrint=fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString();
        val modelPrettyPrint = parseHelper.parse(parserPrettyPrint);
        assertEquals(parserPrettyPrint.toString, fsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString)
    	
    	println("Double pretty print"+"\n");
    	val f = new InMemoryFileSystemAccess()
    	
    	underTestPrettyPrint.doGenerate(modelPrettyPrint.eResource, f);
    	println(f.files);
    	assertEquals(parserPrettyPrint.toString, f.files.get(IFileSystemAccess::DEFAULT_OUTPUT+"sth.whpp").toString)
    }    
}