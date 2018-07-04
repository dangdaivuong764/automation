
private By getIdentifier(String objectName) throws Exception {
		if (null != ReadProperties.get(objectName + "-id")) {
			return By.id(ReadProperties.get(objectName + "-id"));
		} else if (null != ReadProperties.get(objectName + "-xp")) {
			return By.xpath(ReadProperties.get(objectName + "-xp"));
		} else if (null != ReadProperties.get(objectName + "-css")) {
			return By.cssSelector(ReadProperties.get(objectName + "-css"));
		} else if (null != ReadProperties.get(objectName + "-cln")) {
			return By.className(ReadProperties.get(objectName + "-cln"));
		} else if (null != ReadProperties.get(objectName + "-link")) {
			return By.linkText(ReadProperties.get(objectName + "-link"));
		} else if (null != ReadProperties.get(objectName + "-plink")) {
			return By.partialLinkText(ReadProperties.get(objectName + "-plink"));
		} else if (null != ReadProperties.get(objectName + "-tag")) {
			return By.tagName(ReadProperties.get(objectName + "-tag"));
		} else if (null != ReadProperties.get(objectName + "-name")) {
			return By.name(ReadProperties.get(objectName + "-name"));
		} else {
			try {
				if (driver.findElement(By.linkText(objectName)).isDisplayed()) {
					return By.linkText(objectName);
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
			// throw new Exception("Wrong Object type entered");
		}
	}
	
	------------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------------

public class ReadProperties {

    /*
     * http://stackoverflow.com/questions/9902084/how-to-pass-input-from-command-line-to-junit-maven-test-program
     * propertiesConfig can not move to Constant due to hard set in pom.xml mvn -clean -test
     * DpropertiesConfig=DPath/my_file_name.txt
     */
    public static String get(String key) {
        Properties obj = new Properties();
        String value = null;
        key=key.replace(" ", "-");
        String remote = System.getProperty("propertiesConfig");
        if (remote == null || "".equals(remote)) {
            value = getData(obj, key);
        } else {
            try {
                InputStream inputStream = new FileInputStream(remote);
                obj.load(inputStream);
                inputStream.close();
            }
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            value = obj.getProperty(key);
            if (StringUtils.isEmpty(value)) {
                value = getData(obj, key);
            }
        }
        return StringUtils.trim(value);
    }

    private static String getData(Properties obj, String key) {
        try {
            String file = new DataProvider().getPropertiesFile();
            InputStream inputStream = new FileInputStream(file);
            obj.load(inputStream);
            inputStream.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return obj.getProperty(key);
    }

    public List<String> getByPrefix(String prefix) throws Exception {
        Properties obj = new Properties();
        String file = new DataProvider().getPropertiesFile();
        InputStream inputStream = new FileInputStream(file);
        obj.load(inputStream);
        Set<Object> keys = obj.keySet();
        List<String> rs = new ArrayList<String>();
        for (Object k : keys) {
            String key = (String) k;
            if (key.startsWith(prefix)) {
                rs.add(StringUtils.trim(obj.getProperty(key)));
            }
        }
        return rs;
    }

    public void store(String key, String value) {
        try {
            String file = new DataProvider().getPropertiesFile();
            FileInputStream in = new FileInputStream(file);
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(file);
            props.setProperty(key, value);
            props.store(out, null);
            out.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}




public void scrollToView(WebElement element) throws InterruptedException {
		try {
			if (element.getLocation().getX() > 700 && element.getLocation().getY() > 500) {
				scrollTo(element.getLocation().getY() - 100, element.getLocation().getY() - 200);
			}
			if (element.getLocation().getX() > 700 && element.getLocation().getY() > 300) {
				scrollTo(element.getLocation().getY() - 100, element.getLocation().getY() - 200);
			} else if (element.getLocation().getY() > 500) {
				scrollTo(0, element.getLocation().getY() - 200);
			} else {
				scrollTo(0, 0);
			}
			Thread.sleep(500);
		//----------- vu.hoang ------------
		}catch(Exception ex) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
		}
	}

--------------------------------------------
	public void clickListElement(List<WebElement> eles) throws InterruptedException {
		waitForAjax();
		boolean isDisplay = false;
		Assert.assertTrue(eles + " is not present", eles.size() != 0);
		for (int i = 0; i < eles.size(); i++) {
			if (eles.get(i).isDisplayed()) {
				waitForAjax();
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable((eles.get(i))));
				scrollToView(eles.get(i));
				eles.get(i).click();
				waitForAjax();
				isDisplay = true;
			}

		}
		Assert.assertTrue(isDisplay);
	}
------------------------------------------------
public void clickElementInPostition(List<WebElement> eles, int position) throws InterruptedException {
		waitForAjax();
		Assert.assertTrue(eles + " is not present", eles.size() != 0);
		Assert.assertTrue(eles + " is not present", eles.size() >= position);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(eles.get(position)));
		wait.until(ExpectedConditions.elementToBeClickable((eles.get(position))));
		scrollToView(eles.get(position));
		eles.get(position).click();
		waitForAjax();

	}

-------------------------------------------------
public void checkListText(String object, String format, String expected, String require) throws Exception {
		List<WebElement> eles = driver.findElements(getIdentifier(object));
		if (null != require) {
			Assert.assertTrue(object + "is not display ", eles.size() != 0);
		}
		for (int i = 0; i < eles.size(); i++) {
			if (eles.get(i).isDisplayed()) {
				if (null != format && format.equals("matched")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertTrue(object + " " + i + " is not matched " + expected,
							actual.contains(expected.trim()));
				} else if (null != format && format.equals("nearly matched")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertTrue(object + " " + i + " is not matched " + expected,
							actual.toLowerCase().contains(expected.trim().toLowerCase()));
				} else if (null != format && format.equals("nearly")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertEquals(expected.trim().toLowerCase(), actual.trim().toLowerCase());
				} else {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertEquals(expected.trim(), actual.trim());
				}
			}
		}
	}


---------------------------------------------------
public void checkBoxHasValue(String action, String objectName, String value, String doThisStep) throws Exception {
		if (null == doThisStep || "do this step: yes".equals(doThisStep)) {
			waitForAjax();
			List<WebElement> listElement = returnListWebElement(objectName);
			Assert.assertTrue(listElement.size() != 0);
			boolean isDisplay = false;
			for (int i = 0; i < listElement.size(); i++) {
				if (listElement.get(i).isDisplayed()) {
					if (listElement.get(i).getText().contains(value)) {
						WebElement checkbox = listElement.get(i).findElement(By.tagName("input"));
						scrollToView(checkbox);
						if (checkbox.isSelected()) {
							if (!"Checked".equals(action)) {
								checkbox.click();
							}
						} else {
							if ("Checked".equals(action)) {
								checkbox.click();
							}
						}
					}
					waitForAjax();
					isDisplay = true;
				}

			}
			Assert.assertTrue(isDisplay);
		}
	}

	----------------------------------------------
	public void checkListText(String object, String format, String expected, String require) throws Exception {
		List<WebElement> eles = driver.findElements(getIdentifier(object));
		if (null != require) {
			Assert.assertTrue(object + "is not display ", eles.size() != 0);
		}
		for (int i = 0; i < eles.size(); i++) {
			if (eles.get(i).isDisplayed()) {
				if (null != format && format.equals("matched")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertTrue(object + " " + i + " is not matched " + expected,
							actual.contains(expected.trim()));
				} else if (null != format && format.equals("nearly matched")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertTrue(object + " " + i + " is not matched " + expected,
							actual.toLowerCase().contains(expected.trim().toLowerCase()));
				} else if (null != format && format.equals("nearly")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertEquals(expected.trim().toLowerCase(), actual.trim().toLowerCase());
				} else {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertEquals(expected.trim(), actual.trim());
				}
			}
		}
	}
-------------------------------------------------------
public WebElement returnWebElementInPostition(String objectName, int position) throws Exception {
		WebElement webelement;
		WebElement webelementParent = returnParentWebElement(objectName);
		if (webelementParent == null) {
			webelement = driver.findElements(getIdentifier(objectName)).get(position);
		} else {
			webelement = webelementParent.findElements(getIdentifier(objectName)).get(position);
		}
		Assert.assertTrue(objectName + " is not present", webelement != null);
		return webelement;
	}
-----------------------------------------------
public List<WebElement> returnListWebElement(String objectName) throws Exception {
		List<WebElement> webelement;
		WebElement webelementParent = returnParentWebElement(objectName);
		if (webelementParent == null) {
			webelement = driver.findElements(getIdentifier(objectName));
		} else {
			webelement = webelementParent.findElements(getIdentifier(objectName));
		}
		return webelement;
	}
------------------------------------------------------------------------
	public void checkListText(String object, String format, String expected, String require) throws Exception {
		List<WebElement> eles = driver.findElements(getIdentifier(object));
		if (null != require) {
			Assert.assertTrue(object + "is not display ", eles.size() != 0);
		}
		for (int i = 0; i < eles.size(); i++) {
			if (eles.get(i).isDisplayed()) {
				if (null != format && format.equals("matched")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertTrue(object + " " + i + " is not matched " + expected,
							actual.contains(expected.trim()));
				} else if (null != format && format.equals("nearly matched")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertTrue(object + " " + i + " is not matched " + expected,
							actual.toLowerCase().contains(expected.trim().toLowerCase()));
				} else if (null != format && format.equals("nearly")) {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertEquals(expected.trim().toLowerCase(), actual.trim().toLowerCase());
				} else {
					String actual = replaceSomeText(eles.get(i).getText());
					Assert.assertEquals(expected.trim(), actual.trim());
				}
			}
		}
	}

