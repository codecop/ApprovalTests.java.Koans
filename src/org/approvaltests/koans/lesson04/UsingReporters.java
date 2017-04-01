package org.approvaltests.koans.lesson04;

import org.approvaltests.ReporterFactory;
import org.approvaltests.core.ApprovalFailureReporter;
import org.approvaltests.koans.helpers.Koans;
import org.approvaltests.reporters.*;
import org.approvaltests.reporters.windows.TortoiseImageDiffReporter;
import org.junit.Assert;
import org.junit.Test;

/**
 * How to do Koans:
 * Step 1: Press the Run Button (Place cursor on the Method name to run a single method)
 * PC: Ctrl+F11
 * Mac: Command+fn+F11
 * Step 2: Read the name of the Method that Failed
 * Step 3: Fill in the blank (___) to make it pass
 * Step 4: Repeat Until Enlightenment
 * Do not change anything except the blank (___)
 */
@UseReporter(QuietReporter.class)
public class UsingReporters extends Koans
{
  @Test
  @UseReporter(FileLauncherReporter.class)
  public void configuringTheFileLauncherReporter()
  {
    ApprovalFailureReporter reporter = ReporterFactory.getFromAnnotation();
    Assert.assertTrue("Please fill in the blank", reporter instanceof FileLauncherReporter);
    // Approvals.verify("open window!"); // opens Window
  }

  @Test
  public void configuringAClassLevelDefault()
  {
    ApprovalFailureReporter reporter = ReporterFactory.getFromAnnotation();
    Assert.assertTrue(reporter.getClass().getName(), reporter instanceof QuietReporter);
  }

  @Test
  @UseReporter({ ClipboardReporter.class, ImageWebReporter.class, TortoiseImageDiffReporter.class })
  public void configuringMultipleReporters()
  {
    ApprovalFailureReporter reporter = ReporterFactory.getFromAnnotation();
    MultiReporter multi = (MultiReporter) reporter;
    ApprovalFailureReporter second = multi.getReporters()[1];
    Assert.assertTrue(second.getClass().getName(), second instanceof ImageWebReporter);
  }

  @Test
  @UseReporter(FileLauncherReporter.class)
  public void usingTextReportersForInsight() throws Exception
  {
    if (!decode("=fikpKnf").equals("Forty Two"))
    {
      ApprovalFailureReporter reporter = ReporterFactory.getFromAnnotation();
      reporter.report(getPath("Insight.txt"), "");
      Assert.fail("Please fill in the blank");
    }
  }

  @Test
  @UseReporter(ImageWebReporter.class)
  public void usingImageForInsight() throws Exception
  {
    String whatWasTheOldColor = "blue";
    if (!decode("Ycl\\").equals(whatWasTheOldColor.toLowerCase()))
    {
      ApprovalFailureReporter reporter = ReporterFactory.getFromAnnotation();
      reporter.report(getPath("NewImage.png"), getPath("OldImage.png"));
      Assert.fail("Please fill in the blank");
    }
  }
}
