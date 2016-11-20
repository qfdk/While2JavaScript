package me.qfdk.esir.wh.tests;

import com.google.inject.Inject;
import java.util.Map;
import me.qfdk.esir.wh.WhInjectorProvider;
import me.qfdk.esir.wh.wh.wh;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(WhInjectorProvider.class)
@SuppressWarnings("all")
public class PrettyPrinterTest {
  @Inject
  private IGenerator underTest;
  
  @Inject
  private IGenerator underTestPrettyPrint;
  
  @Inject
  private ParseHelper<wh> parseHelper;
  
  @Test
  public void testDefinition() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function bonjour : read X,Y,Z,E%nop%write Z");
      _builder.newLine();
      final wh model = this.parseHelper.parse(_builder);
      final InMemoryFileSystemAccess fsa = new InMemoryFileSystemAccess();
      Resource _eResource = model.eResource();
      this.underTest.doGenerate(_eResource, fsa);
      Map<String, CharSequence> _files = fsa.getFiles();
      InputOutput.<Map<String, CharSequence>>println(_files);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("function bonjour: ");
      _builder_1.newLine();
      _builder_1.append("read X, Y, Z, E");
      _builder_1.newLine();
      _builder_1.append("%");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("nop");
      _builder_1.newLine();
      _builder_1.append("%");
      _builder_1.newLine();
      _builder_1.append("write Z");
      _builder_1.newLine();
      _builder_1.newLine();
      String _string = _builder_1.toString();
      Map<String, CharSequence> _files_1 = fsa.getFiles();
      CharSequence _get = _files_1.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_1 = _get.toString();
      Assert.assertEquals(_string, _string_1);
      Map<String, CharSequence> _files_2 = fsa.getFiles();
      boolean _containsKey = _files_2.containsKey((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      Assert.assertTrue(_containsKey);
      Map<String, CharSequence> _files_3 = fsa.getFiles();
      CharSequence _get_1 = _files_3.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_2 = _get_1.toString();
      boolean _contains = _string_2.contains("function ");
      Assert.assertTrue(_contains);
      Map<String, CharSequence> _files_4 = fsa.getFiles();
      CharSequence _get_2 = _files_4.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_3 = _get_2.toString();
      boolean _contains_1 = _string_3.contains(": \nread ");
      Assert.assertTrue(_contains_1);
      Map<String, CharSequence> _files_5 = fsa.getFiles();
      CharSequence _get_3 = _files_5.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_4 = _get_3.toString();
      boolean _contains_2 = _string_4.contains("read ");
      Assert.assertTrue(_contains_2);
      Map<String, CharSequence> _files_6 = fsa.getFiles();
      CharSequence _get_4 = _files_6.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_5 = _get_4.toString();
      boolean _contains_3 = _string_5.contains("write ");
      Assert.assertTrue(_contains_3);
      Map<String, CharSequence> _files_7 = fsa.getFiles();
      CharSequence _get_5 = _files_7.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_6 = _get_5.toString();
      boolean _contains_4 = _string_6.contains("%");
      Assert.assertTrue(_contains_4);
      Map<String, CharSequence> _files_8 = fsa.getFiles();
      CharSequence _get_6 = _files_8.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_7 = _get_6.toString();
      boolean _contains_5 = _string_7.contains(" write ");
      Assert.assertFalse(_contains_5);
      Map<String, CharSequence> _files_9 = fsa.getFiles();
      CharSequence _get_7 = _files_9.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_8 = _get_7.toString();
      boolean _contains_6 = _string_8.contains(" read ");
      Assert.assertFalse(_contains_6);
      Map<String, CharSequence> _files_10 = fsa.getFiles();
      CharSequence _get_8 = _files_10.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_9 = _get_8.toString();
      boolean _contains_7 = _string_9.contains("% ");
      Assert.assertFalse(_contains_7);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Inject
  private JavaIoFileSystemAccess fileAccess;
  
  @Test
  public void testDoublePrettyPrinter() {
    try {
      this.fileAccess.setOutputPath(".");
      CharSequence _readTextFile = this.fileAccess.readTextFile("test.wh");
      String _string = _readTextFile.toString();
      final wh model = this.parseHelper.parse(_string);
      final InMemoryFileSystemAccess fsa = new InMemoryFileSystemAccess();
      Resource _eResource = model.eResource();
      this.underTest.doGenerate(_eResource, fsa);
      Map<String, CharSequence> _files = fsa.getFiles();
      String _plus = (_files + "\n");
      InputOutput.<String>println(_plus);
      Map<String, CharSequence> _files_1 = fsa.getFiles();
      CharSequence _get = _files_1.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      final String parserPrettyPrint = _get.toString();
      final wh modelPrettyPrint = this.parseHelper.parse(parserPrettyPrint);
      String _string_1 = parserPrettyPrint.toString();
      Map<String, CharSequence> _files_2 = fsa.getFiles();
      CharSequence _get_1 = _files_2.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_2 = _get_1.toString();
      Assert.assertEquals(_string_1, _string_2);
      InputOutput.<String>println(("Double pretty print" + "\n"));
      final InMemoryFileSystemAccess f = new InMemoryFileSystemAccess();
      Resource _eResource_1 = modelPrettyPrint.eResource();
      this.underTestPrettyPrint.doGenerate(_eResource_1, f);
      Map<String, CharSequence> _files_3 = f.getFiles();
      InputOutput.<Map<String, CharSequence>>println(_files_3);
      String _string_3 = parserPrettyPrint.toString();
      Map<String, CharSequence> _files_4 = f.getFiles();
      CharSequence _get_2 = _files_4.get((IFileSystemAccess.DEFAULT_OUTPUT + "sth.whpp"));
      String _string_4 = _get_2.toString();
      Assert.assertEquals(_string_3, _string_4);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
