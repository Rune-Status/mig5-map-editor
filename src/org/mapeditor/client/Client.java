package org.mapeditor.client;

/* client - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.CRC32;
import java.util.zip.GZIPInputStream;

import javax.swing.JPanel;

import org.mapeditor.Main;
import org.mapeditor.editor.Editor;
import org.mapeditor.gui.GUI;

public final class Client extends GameShell {
	
	public static Hashtable<Long, GameObject> objects = new Hashtable<Long, GameObject>();
	public static int playerRenderY = 8256;
	public static int playerRenderX = 6592;
	public static boolean locationToggle;
	public static int mapX = 47; //41
	public static int mapY = 45; //47
	public static int mouseX = -1;
	public static int mouseY = -1;
	public static int squareX = 0;
	public static int squareY = 0;
	
	public static int playerLocalX = 30;
	public static int playerLocalY = 30;

	/**
	 * Most of custom stuff will be here
	 */
	static boolean wheelDragEnabled;
	static int wheelRotatePitch = -1;
	static int wheelRotateYaw = -1;
	static int wheelMouseX = -1;
	static int wheelMouseY = -1;
	static RSString allocatedMemory = RSString
			.createRSString("Allocated memory");
	static long allocatingTime = 0L;
	static long allocateGcTime;
	static RSString allocatingMemory = RSString
			.createRSString("Allocating memory");
	/**
	 * End of custom stuff
	 */
	static final long serialVersionUID = 1517716723551304849L;
	static Sprite leftFlameSprite;
	static int[] compassDestination;
	static int anInt1132 = 0;
	static boolean canMute = false;
	static int musicVolume2;
	static SignlinkNode dnsNode;
	static RSString lre = RSString.createRSString("@lre@");
	static byte[][] pooledBuffer3 = new byte[50][];
	static int[] maxStats = new int[25];
	static Sprite[] mapDot;
	static Sprite[] crosses;
	static int flagX = 0;
	static CacheFileWorker cacheIndex3;
	static int keyCode;
	static RSString cya = RSString.createRSString("@cya@");
	static Huffman huffman;
	static RSString select = RSString.createRSString("Select");
	static RSString membersObject = RSString.createRSString("Members object");
	static Cache spotAnimCache = new Cache(64);
	static RSString enterMessageToSendTo = RSString
			.createRSString("Enter message to send to ");
	static RSString runescapeIsLoadingPleaseWait = RSString
			.createRSString("RuneScape is loading )2 please wait)3)3)3");
	static int lastMouseX = 0;
	static RSString percentDns = RSString.createRSString("(Udns");
	static int[] resultIds = new int[100];
	static int[] groundAlphas;
	static FileSystem varpFetcher;
	static RSString headiconspkString = RSString.createRSString("headicons_pk");
	static RSString loadedGameScreen = RSString
			.createRSString("Loaded gamescreen");
	static RSString backRight1 = RSString.createRSString("backright1");
	static long lastConnection;
	static Cache aClass19_178 = new Cache(500);
	public static byte[][][] underlayRotations;
	static boolean scrollBarClicked = false;
	static int chatboxScrollerPos = 0;
	static BigInteger RSA_EXPONENT = new BigInteger("65537");
	static CacheFileWorker cacheIndex2;
	static RSString addIgnore = RSString.createRSString("Add ignore");
	static int anInt865 = 0;
	static IndexedSprite redStone2_3;
	static RSString p11full = RSString.createRSString("p11_full");
	static IndexedSprite mapBack;
	static RSString couldNotCompleteLogin = RSString
			.createRSString("Could not complete login)3");
	static RSString yourAccountHasBeenDisabled = RSString
			.createRSString("Your account has been disabled)3");
	static int[] soundDelays = new int[50];
	static Cache musicCache;
	static int blinkingTabID = -1;
	static int walkableChatboxId = -1;
	static RSString loggedIn = RSString.createRSString(" has logged in)3");
	static RSString pressRecoverALockedAccount = RSString
			.createRSString("Press (Wrecover a locked account(W on front page)3");
	static int[][] xtea;
	static RSString gr3 = RSString.createRSString("@gr3@");
	static volatile boolean appletFocused = true;
	static RSString loadingInterfaces = RSString
			.createRSString("Loading interfaces )2 ");
	static boolean updateCamera = false;
	static Image loadingTextImage;
	static GameShell currentScreen = null;
	static IndexedSprite redStone1_3;
	static RSString loadingFonts = RSString.createRSString("Loading fonts )2 ");
	static int[] bigY = new int[4000];
	static int tabareaYellowbox = -1;
	static int packetOpcode = 0;
	static int keyCacheLen = 0;
	static int renderZ;
	static int[] spriteOffsetX;
	static Deque projectileList = new Deque();
	static long friendHash = 0L;
	static Object anObject821 = new Object();
	static RSString overlaymultiwayString = RSString
			.createRSString("overlay_multiway");
	static RSString runescapeHasBeenUpdated = RSString
			.createRSString("RuneScape has been updated(Q");
	static int activeComponentId = -1;
	static RSString hitmarksString = RSString.createRSString("hitmarks");
	static int spellUsableOn;
	static byte[] buffer = new byte[520];
	static int lastMouseDragAction = 0;
	static FileSystem interfaceFetcher;
	static int hintNpcID = 0;
	static RSString friendlistIsFull = RSString
			.createRSString("Your friendlist is full)3 Max of 100 for free users)1 and 200 for members");
	static int soundEffectVolume = 127;
	static int[] spriteWidth;
	static RSString whi2 = RSString.createRSString("@whi@ )4 ");
	static IndexedSprite redStone1_4;
	static RSString shake = RSString.createRSString("shake:");
	static int fullScreenInterfaceId = -1;
	static RSString backvmid3 = RSString.createRSString("backvmid3");
	static RSString alreadyInFlist = RSString
			.createRSString(" is already on your friend list");
	static RSString flash2 = RSString.createRSString("flash2:");
	static int mapFunctionsCount = 0;
	static int tradeMode = 0;
	static int[] shakingPower = new int[5];
	static int maximumTextOnScreen = 50;
	static long[] ignoreList = new long[100];
	static KeyboardHandler keyboardHandler = new KeyboardHandler();
	static int[] textSpokenColor = new int[maximumTextOnScreen];
	static int[] textSpokenWidth = new int[maximumTextOnScreen];
	static int[] textSpokenY = new int[maximumTextOnScreen];
	static RSString[] textSpokenMessage = new RSString[maximumTextOnScreen];
	static FileSystem itemModelFetcher;
	static FileSystem spotAnimationModelFetcher;
	static int[] textSpokenCycle = new int[maximumTextOnScreen];
	static int lookFromAngle;
	static RSString fpsString = RSString.createRSString("Rough fps:");
	static int mapFilesFetchCount = 0;
	static int[] textSpokenEffect = new int[maximumTextOnScreen];
	static int[] textSpokenHeight = new int[maximumTextOnScreen];
	static int walkableInventoryId = -1;
	static int splitPrivateChat = 0;
	static GraphicsBuffer logoGraphicsBuffer;
	static GraphicsBuffer backTop1Buffer;
	static int[] textSpokenX = new int[maximumTextOnScreen];
	static Timer screenTimer;
	static RSString closeInterface = RSString
			.createRSString("Please close the interface you have open before using (Wreport abuse(W");
	static RSString close = RSString.createRSString("Close");
	static RSString glow1 = RSString.createRSString("glow1:");
	static RSString m = RSString.createRSString("m");
	static CacheFileWorker cacheIndex5;
	static RSString continueString = RSString.createRSString("Continue");
	static RSString muteOptionOn = RSString
			.createRSString("Moderator option: Mute player for 48 hours: <ON>");
	static RSString asItWasUsedToBreakOurRules = RSString
			.createRSString("as it was used to break our rules)3");
	static RSString loginLimitExceeded = RSString
			.createRSString("Login limit exceeded)3");
	static JPanel clientFrame;
	static RSString redstone2 = RSString.createRSString("redstone2");
	static RSString cr1 = RSString.createRSString("@cr1@");
	static RSString wishesTrade = RSString
			.createRSString("wishes to trade with you)3");
	static IndexedSprite[] sideIcons;
	static RSString loadedFonts = RSString.createRSString("Loaded fonts");
	static RSString pleaseWait = RSString.createRSString("Please wait)3)3)3");
	static int anInt1526;
	static Cache varbitCache = new Cache(64);
	static boolean waitForNextChat = false;
	static CacheFileWorker cacheIndex7;
	static int atInventoryInterfaceType = 0;
	static int anInt1439 = 0;
	static volatile int currentMouseY = -1;
	static int anInt1442 = 0;
	static FileSystem itemFetcher;
	static RSString remove = RSString.createRSString("Remove");
	static IndexedSprite titleBox;
	static int loadingScreenType = 0;
	static RSByteBuffer aClass3_Sub12_1448;
	static int localPlayerCount = 0;
	static long lastJs5ConnectionTime;
	static int[] headColors = { 9104, 10275, 7595, 3610, 7975, 8526, 918,
			38802, 24466, 10145, 58654, 5027, 1457, 16565, 34991, 25486 };
	static FileSystem npcModelFetcher;
	static int menuType;
	static int friendsListAction = 0;
	static RSString or3 = RSString.createRSString("@or3@");
	static GraphicsBuffer underTitleBoxGraphicsBuffer;
	static Cache npcModelCache = new Cache(50);
	static RSString percent3 = RSString.createRSString("(U3");
	static FileSystem skinsFetcher;
	static RSString thisServerIsBeingUpdated = RSString
			.createRSString("The server is being updated)3");
	static int[] localEntityIndices = new int[1000];
	static int hintIconX = 0;
	static RSString to = RSString.createRSString("To");
	static RSString iconsreDrawn = RSString.createRSString("Icons redrawn");
	static Queue aClass30_2033 = new Queue();
	static RSString drop = RSString.createRSString("Drop");
	static RSString flash1 = RSString.createRSString("flash1:");
	static RSString spaceArrowCya = RSString.createRSString(" )2> @cya@");
	static RSString loadingWorldpack = RSString
			.createRSString("Loading wordpack )2 ");
	static int worldPort2;
	static GraphicsBuffer tabAreaBuffer;
	static RSString membersArea = RSString
			.createRSString("You are standing in a members)2only area)3");
	static byte[] validChars = new byte[] { 95, 97, 98, 99, 100, 101, 102, 103,
			104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116,
			117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56,
			57 };
	static int[] soundTypes = new int[50];
	public static int count = 0;
	static GraphicsBuffer leftFlameGraphicsBuffer;
	static int sizeY;
	static int iKitLen;
	static GraphicsBuffer backhmid2Buffer;
	static RSString scrollbarString = RSString.createRSString("scrollbar");
	static GraphicsBuffer upperSideIconsBuffer;
	static RSString noResponseFromServer = RSString
			.createRSString("No response from server)3");
	static Sprite compass;
	static int publicChatMode = 0;
	static int fullScreenChatboxId = -1;
	static RSString loadingTitleScreen = RSString
			.createRSString("Loading title screen )2 ");
	static Sprite landScapeAsSprite;
	static boolean[] skillEnabled = { true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, false, true, false, false };
	static boolean holdingArrowKeys = false;
	static GraphicsBuffer backRight2Buffer;
	static GraphicsBuffer fullscreenGraphics;
	static int[] bigX = new int[4000];
	static RSSocket worldConnection;
	static RSString aClass63_1727 = RSString.createRSString(" )2> @yel@");
	static RSString redstone3 = RSString.createRSString("redstone3");
	static CacheFileWorker cacheIndex11;
	static RSFont regularFont;
	static int[] anIntArray1238;
	static RSString percentString = RSString.createRSString("(U");
	static RSString hundredPercent = RSString.createRSString("(X100(U(Y");
	static GraphicsBuffer backvmid3Buffer;
	static RSString youNeedAMembersAccountToLogin = RSString
			.createRSString("You need a members account to login to this world)3");
	static RSString rightBracketPercent = RSString.createRSString("(U(Y");
	static int totalItems;
	static FileSystem skeletonFetcher;
	static int lastClickX = 0;
	static int lastClickY = 0;
	static int[] tabInterfaceIDs = new int[] { -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1 };
	static RSString accountLockedAsWeSuspectItHasBeenStolen = RSString
			.createRSString("Account locked as we suspect it has been stolen)3");
	static IndexedSprite backBase2;
	static volatile int currentClickX = 0;
	static boolean loopMusic2;
	static int crossX = 0;
	static RSString enterYourUsernamePassowrd = RSString
			.createRSString("Enter your username (V password)3");
	static int anInt478 = -1;
	static int focusedItemSlot = 0;
	static int flashEffectCycle = 0;
	static Cache objectDefCache = new Cache(64);
	public static int height;
	static Cache iKitCache = new Cache(64);
	static RSString invback = RSString.createRSString("invback");
	static RSString use = RSString.createRSString("Use");
	static RSString loadingConfigs = RSString
			.createRSString("Loading config )2 ");
	static Cache itemModelCache = new Cache(50);
	static int renderY;
	static RSString spaceArrow = RSString.createRSString(" )2>");
	static RSString yourAccountIsAlreadyLoggedIn = RSString
			.createRSString("Your account is already logged in)3");
	static RSString loginServerOffline = RSString
			.createRSString("Login server offline)3");
	static GraphicsBuffer leftTorchGraphicsBuffer;
	static FileSystem overlayFetcher;
	static int hintIconY = 0;
	static long[] fpsCache = new long[32];
	public static int modeWhere = 0;
	static int[] mapFunctionsY = new int[1000];
	static IndexedSprite titleButton;
	static RSString or1 = RSString.createRSString("@or1@");
	static RSString reportAbuse = RSString.createRSString("Report abuse");
	static RSString white = RSString.createRSString("white:");
	static IndexedSprite backBase1;
	static RSString enterMount = RSString.createRSString("Enter amount:");
	static int previousOpcode = 0;
	static int cameraYawModifier = 0;
	static int currentMusic = -1;
	static int energy = 0;
	static int fpsCacheLength;
	static byte[][] spritePixels;
	static RSString unexpectedServerResponse = RSString
			.createRSString("Unexpected server response: ");
	static int currentKeyCode = 0;
	static RSString cyan = RSString.createRSString("cyan:");
	static int one = 1;
	static int currentRotation = 0;
	static int[][] anIntArrayArray429 = new int[104][104];
	static RSString wishesDuel = RSString
			.createRSString("wishes to duel with you)3");
	static RSString muteOptionOff = RSString
			.createRSString("Moderator option: Mute player for 48 hours: <OFF>");
	static int[][][] anIntArrayArrayArray438;
	static RSString classic = RSString.createRSString("Classic");
	static RSString invalidUsernameOrPassword = RSString
			.createRSString("Invalid username or password)3");
	static int hintImageY = 0;
	static RSString from = RSString.createRSString("From");
	static int musicVolume = 255;
	static volatile int mouseIdleTime = 0;
	static int[] chatTypes = new int[100];
	static int yellowBoxHoverTimer = 0;
	static boolean[] interfaceLoaded;
	static int gameLoopLength;
	static int fps = 0;
	static int[] anIntArray558;
	static int oldHeight = 0;
	static int inserting = 0;
	static RSString malformedLoginPacket = RSString
			.createRSString("Malformed login packet)3");
	static RSString pleaseWait1MinuteAndTryAgain = RSString
			.createRSString("Please wait 1 minute and try again)3");
	static Cache modelCache = new Cache(50);
	static long[] gameLoopTime = new long[32];
	static RSString backbase1 = RSString.createRSString("backbase1");
	static RSString mapback = RSString.createRSString("mapback");
	static RSString yel = RSString.createRSString("@yel@");
	static RSString backbase2 = RSString.createRSString("backbase2");
	static int fourthOpcode = 0;
	static long[] friendList = new long[200];
	static RSString spaceX = RSString.createRSString(" x");
	static int entityUpdateCount = 0;
	static Signlink errorSignlink;
	static int chatEffects = 0;
	static int[] anIntArray679;
	static int minimapHeight = -1;
	static RSString errorLoadingYourProfile = RSString
			.createRSString("Error loading your profile)3");
	static RSString fromYourFriendListFirst = RSString
			.createRSString(" from your friend list first");
	static HashTable aClass40_1146 = new HashTable(4096);
	static boolean showNumbersOnOptions = false;
	static volatile int currentClickY = 0;
	static int systemUpdateTime = 0;
	static RSString nothing = RSString.createRSString("");
	static RSString[] menuActionName = new RSString[500];
	static RSString glow2 = RSString.createRSString("glow2:");
	static int chatboxYellowbox = -1;
	static RSString pleaseUseADifferentWorld = RSString
			.createRSString("Please use a different world)3");
	public static boolean[] heldKeys = new boolean[112];
	static int lastMouseAction = 0;
	static FileSystem sequenceFetcher;
	static Sprite multiIconSprite;
	static int tempMusicDelay = 0;
	static IndexedSprite invBack;
	static RSString wave2 = RSString.createRSString("wave2:");
	static RSString connectingToFriend = RSString
			.createRSString("Connecting to friendserver");
	static RSString yelStarV = RSString.createRSString("@yel@*V");
	static int extendFriendList = 0;
	static RSString spaceXspace = RSString.createRSString(" x ");
	static int anInt1368 = 0;
	static RSString errorConnectingToServer = RSString
			.createRSString("Error connecting to server)3");
	static int femaleSprite = -1;
	static int loadingBarPercent = 10;
	static int lookAtAngle;
	static RSString tradeReq = RSString.createRSString(":tradereq:");
	static int anInt1501 = 0;
	static RSString k = RSString.createRSString("k");
	static int spriteDrawY = -1;
	static IndexedSprite redStone2_2;
	static RSString andChooseTheCreateAccount = RSString
			.createRSString("and choose the (Wcreate account(W");
	static SignlinkNode worldConnectionNode;
	static int cameraPitchModifier = 0;
	static int menuOffsetY;
	static boolean messagePromptRaised = false;
	static boolean destroyed = false;
	static GraphicsBuffer rightFlameGraphicsBuffer;
	static int[] anIntArray743 = new int[] { 8, 11, 4, 6, 9, 7, 10 };
	static long[] messageCounterArray = new long[100];
	static CacheFileWorker[] aClass18_Sub1Array745 = new CacheFileWorker[256];
	static RSString crossString = RSString.createRSString("cross");
	static RSString thisWorldIsFull = RSString
			.createRSString("This world is full)3");
	static int packetSize = 0;
	static RSString skill = RSString.createRSString("skill)2");
	static RSString runesString = RSString.createRSString("runes");
	static RSString mapdotsString = RSString.createRSString("mapdots");
	static RSString x = RSString.createRSString("(X");
	static RSString spaceX2 = RSString.createRSString(" (X");
	static RSString b12full = RSString.createRSString("b12_full");
	static int mouseButtons = 0;
	static int[] anIntArray616 = new int[] { 16, 32, 64, 128 };
	static RSString gr1 = RSString.createRSString("@gr1@");
	public static boolean showFps = true;
	static RSString mapedgeString = RSString.createRSString("mapedge");
	static RSString spaceWhi = RSString.createRSString(" @whi@");
	static int coordinateY;
	static int[] anIntArray574;
	static int pooledBuffer1Len = 0;
	static RSString connectionTimedOut = RSString
			.createRSString("Connection timed out)3");
	public static byte[][] mapFileBuffer;
	static int varpLen;
	static RSString enterNameOfFriendToAddToList = RSString
			.createRSString("Enter name of friend to add to list");
	static int fifty = 50;
	static volatile int currentMouseX = -1;
	static RSString seconds = RSString.createRSString(" seconds)3");
	static Cache underlayCache = new Cache(64);
	static RSString loadingGameScreen = RSString
			.createRSString("Loading game screen )2 ");
	static RSString acceptTrade = RSString.createRSString("Accept trade");
	public static volatile int currenMouseAction = 0;
	static int spriteLength;
	static int loginStage = 0;
	static int keyChar;
	static RSString isAlreadyOnYourIgnoreList = RSString
			.createRSString(" is already on your ignore list");
	static FileSystem varbitFetcher;
	static GraphicsBuffer backLeft1Buffer;
	static int parentID = -1;
	static Npc[] localNpcs = new Npc[16384];
	public static boolean[][][] heightMod;
	static SignlinkNode js5ConnectionNode;
	static int activeGameScreen = -1;
	static int scrollPosition = 0;
	static int sleepModifier2 = 20;
	static int friendsCount = 0;
	static int currentBaseX;
	static RSFont[] fonts;
	static RSString loadedWorldPack = RSString
			.createRSString("Loaded wordpack");
	static Cache overlayCache = new Cache(64);
	static GraphicsBuffer backRight1Buffer;
	static RSString memory = RSString.createRSString("Mem:");
	static Cache animationCache = new Cache(100);
	static FileSystem objectFetcher;
	static RSString pleaseContactCustomerSupport = RSString
			.createRSString("Please contact customer support)3");
	static int[] spriteHeight;
	static RSString loadedSprites = RSString.createRSString("Loaded sprites");
	static RSString red = RSString.createRSString("@red@");
	static boolean secondMapRegion = false;
	static RSString nullString = RSString.createRSString("null");
	static boolean titleScreenLoaded;
	static Cache aClass19_638 = new Cache(10);
	static RSString YourIgnoreListIsFullMaxOf100Hit = RSString
			.createRSString("Your ignore list is full)3 Max of 100 hit");
	static IndexedSprite redStone1;
	static int[] tabAreaOffsets;
	static RSString cabbage = RSString.createRSString("Cabbage");
	static GraphicsBuffer minimapBuffer;
	static RSString backLeft1 = RSString.createRSString("backleft1");
	static RSString backhmid1 = RSString.createRSString("backhmid1");
	static RSString thisComputersAddressHasBeenBlocked = RSString
			.createRSString("This computers address has been blocked");
	static int anInt335 = 0;
	static RSSocket worldConnection2;
	static RSString checkingForUpdates = RSString
			.createRSString("Checking for updates )2 ");
	static RSString headiconsprayerSprite = RSString
			.createRSString("headicons_prayer");
	static volatile boolean redrawGameframe = true;
	static int renderYaw;
	static RSString sidePanelreDrawn = RSString
			.createRSString("Side panel redrawn");
	public static byte[][][] underlayIds;
	static RSString loadedConfigs = RSString.createRSString("Loaded config");
	static byte[] musicBuffer;
	static RSByteBuffer chatBuffer = new RSByteBuffer(new byte[5000]);
	static Player myPlayer;
	static RSString thousand = RSString.createRSString("K");
	static RSString loadedInputHandler = RSString
			.createRSString("Loaded input handler");
	static RSString spaceGre = RSString.createRSString(" @gre@");
	static int[] chatColors = { 16776960, 16711680, 65280, 65535, 16711935,
			16777215 };
	static RSString thousand2 = RSString.createRSString("K");
	static RSFont fancyFont;
	static RSString backvmid1 = RSString.createRSString("backvmid1");
	static RSString attack = RSString.createRSString("Attack");
	static int anInt290 = 0;
	static RSString pleaseShortenSearch = RSString
			.createRSString("No matching objects found)1 please shorten search");
	static RSString toPlayOnThisWorldMoveToAFreeAreaFirst = RSString
			.createRSString("To play on this world move to a free area first");
	static RSString million = RSString.createRSString("M");
	static RSString dot = RSString.createRSString(")3");
	static RSString selectedItemName = null;
	static RSString tradeCompete = RSString.createRSString("Trade)4compete");
	static int changePorts = 0;
	static int[] currentRegions;
	public static int[][][] tileHeight = new int[4][105][105];
	static RSString loadingIgnore = RSString
			.createRSString("Loading ignore list");
	static int anInt308 = 0;
	static int currentBaseY;
	static int anInt964 = 0;
	static RSString badSession = RSString.createRSString("Bad session id)3");
	static boolean redrawChatbox = false;
	static int maleSprite = -1;
	static boolean sidePanelRedrawn = false;
	static boolean iconsRedrawn = false;
	static RSString pleaseCheckYourMessageCentreForDetails = RSString
			.createRSString("Please check your message)2centre for details)3");
	static RSString ok = RSString.createRSString("Ok");
	static RSString[] friendsNames = new RSString[200];
	static RSString connectionLost = RSString.createRSString("Connection lost");
	static int musicFileIndex;
	static CacheFileWorker cacheIndex6;
	static int cycleRate = 0;
	static GraphicsBuffer gameScreenBuffer;
	static GraphicsBuffer objectScreenBuffer;
	static int[] anIntArray2057;
	static CacheFileWorker cacheIndex10;
	static int lookFromX;
	static boolean allowMemberItems = false;
	static RSString chatPanelreDrawn = RSString
			.createRSString("Chat panel redrawn");
	static RSString arrowLre = RSString.createRSString(" )2> @lre@");
	static int[] sceneGraphTypes = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3 };
	static int pathFound = 0;
	public static int worldId = 1;
	static int[] xpForLevel = new int[99];
	static RSByteBuffer aClass3_Sub12_1667;
	static long serverSessionKey = 0L;
	static RSString spellTooltip = null;
	static int lookFromHeight;
	static RSString createFreeAccount = RSString
			.createRSString("Create a free account");
	static RSString cancel = RSString.createRSString("Cancel");
	static RSString million2 = RSString.createRSString(" million");
	static RSString backTop1 = RSString.createRSString("backtop1");
	static byte[][] pooledBuffer1 = new byte[1000][];
	static int friendStatus = 0;
	static HashTable aClass40_14 = new HashTable(4096);
	static int inTutIsland = 0;
	static byte[][][] aByteArrayArrayArray12;
	static RSString unableToFind = RSString.createRSString("Unable to find ");
	static RSString chatback = RSString.createRSString("chatback");
	static int[][] cost = new int[104][104];
	static boolean flagged = false;
	static IndexedSprite redStone2;
	static int hintPlayerID = 0;
	static RSString colonSpace = RSString.createRSString(":  ");
	static FileSystem iKitModelFetcher;
	static int[] shakingPitchMod = new int[5];
	static int[] localNpcIndices = new int[16384];
	static RSString youHaveOnlyJustLeftAnotherWorld = RSString
			.createRSString("You have only just left another world)3");
	static Signlink gameSignlink;
	static int inputDialogState = 0;
	static Sprite[] skullIcon;
	static IndexedSprite redStone3;
	static int minimapBlackout = 0;
	static int spriteTrimWidth;
	static RSString enterNameOfFriendToDeleteFromList = RSString
			.createRSString("Enter name of friend to delete from list");
	static RSString redstone1 = RSString.createRSString("redstone1");
	static RSString titleButtonString = RSString.createRSString("titlebutton");
	static int canvasRefreshCycle = 500;
	static volatile long appletLauchTimer = 0L;
	static int lastCoordY = 0;
	static long aLong1432;
	static RSFont smallFont;
	static volatile long currentClickTime = 0L;
	static Sprite mapEdge;
	static RSString percent2 = RSString.createRSString("(U2");
	static RSString pressChangeYourPasswordOnFrontPage = RSString
			.createRSString("Press (Wchange your password(W on front page)3");
	static RSString privateChat = RSString.createRSString("Private chat");
	static int messageCounter = 0;
	static int[] currentExp = new int[25];
	static Cache itemSpriteCache = new Cache(100);
	static int anInt116;
	static int redrawTimer = 0;
	static RSString gre = RSString.createRSString("@gre@");
	static int anInt118;
	static RSString acceptChallenge = RSString
			.createRSString("Accept challenge");
	static boolean gameframeLoaded = false;
	static RSString off = RSString.createRSString("Off");
	static int dragInterfaceId = 0;
	static RSString existingUser = RSString.createRSString("Existing user");
	static GraphicsBuffer backvmid2Buffer;
	static RSString glow3 = RSString.createRSString("glow3:");
	static CacheFileWorker cacheIndex4;
	static int retryDelay = 0;
	static RSString pleaseReloadThisPage = RSString
			.createRSString("Please reload this page)3");
	static int[] pressedKeyCodeCache = new int[128];
	static int anInt197;
	static RSString welcomeToRunescape = RSString
			.createRSString("Welcome to RuneScape");
	static SeekableFile[] indexFile = new SeekableFile[12];
	static int coordinateX;
	static int[] menuActionOpcode = new int[500];
	static int updateServerStage = 0;
	static RSString connectingToServer = RSString
			.createRSString("Connecting to server)3)3)3");
	static volatile int keyboardIdleTime = 0;
	static int loopCycle = 0;
	static RSString loadingPleaseWait = RSString
			.createRSString("Loading )2 please wait)3");
	public static byte[][][] underlayShapes;
	static CacheFile referenceCache;
	static RSString offline = RSString.createRSString("Offline");
	static FontMetrics loadingFontMetrics;
	static RSString underScore = RSString.createRSString("_");
	static RSString fromYourIgnoreListFirst = RSString
			.createRSString(" from your ignore list first");
	static int[] groundHues;
	static long lastClickTime = 0L;
	static CacheFileWorker cacheIndex1;
	static FileSystem npcFetcher;
	static GraphicsBuffer chatboxAreaBuffer;
	static int purpleColorTimer = 0;
	static int anInt1332 = 99;
	static HashTable aClass40_1335 = new HashTable(4096);
	static RSString on = RSString.createRSString("On");
	static int one2 = 1;
	static int crossState = 0;
	static int dragTo = 0;
	static boolean aBoolean311 = false;
	static RSString[] chatMessages = new RSString[100];
	static int hintImageX = 0;
	public static byte[][] landscapeFileBuffer;
	static GraphicsBuffer titleBoxGraphicsBuffer;
	static RSString aClass63_324 = RSString.createRSString(")1");
	static int reportInterfaceID = -1;
	static RSString logo = RSString.createRSString("logo");
	static int[] flameRotation = new int[256];
	static int spriteDrawX = -1;
	static RSString modIconsString = RSString.createRSString("mod_icons");
	static MouseHandler mouseHandler = new MouseHandler();
	static MouseWheel mouseWheel = new MouseWheel();
	static int loadingStage = 0;
	static int weight = 0;
	static RSString enterObjectName = RSString
			.createRSString("Enter object name");
	static FileSystem interfaceSpriteFetcher;
	static RSString buttonNearTheTopOfThatPage = RSString
			.createRSString("button near the top of that page)3");
	public static byte[][][] tileSettings = new byte[4][104][104];
	static RSByteBuffer aClass3_Sub12_2087 = new RSByteBuffer(8);
	static RSString fpsOn = RSString.createRSString("::fpson");
	static int lookFromSpeed;
	static RSString world = RSString.createRSString("World");
	static RSString[] playerActions = new RSString[5];
	static int[] soundIds = new int[50];
	static int[] anIntArray7 = new int[256];
	static RSString spaceYel = RSString.createRSString(" @yel@");
	static RSString mapsceneString = RSString.createRSString("mapscene");
	static int[] anIntArray9;
	static RSString[] resultNames = new RSString[100];
	static RSSocket updateServerConnection;
	public static int[] currentLandscapeFiles;
	static RSString tooManyConnectionsFromYourAddress = RSString
			.createRSString("Too many connections from your address)3");
	public static int cameraYaw = 0;
	static Cache varpCache = new Cache(64);
	static RSString colonWithSpace = RSString.createRSString(": ");
	static int worldPort;
	static FileSystem underlayFetcher;
	static int walkableInterfaceId = -1;
	static Deque classCheckCache = new Deque();
	static int menuOffsetX;
	static int renderPitch;
	static boolean[] shaking = new boolean[5];
	static boolean redrawChatboxButtons = false;
	static int[] chatAreaOffsets;
	static RSString or2 = RSString.createRSString("@or2@");
	static RSString enterNameOfPlayerToDeleteFromList = RSString
			.createRSString("Enter name of player to delete from list");
	static RSString flash3 = RSString.createRSString("flash3:");
	static int[] currentStats = new int[25];
	static HashTable rsStringCache;
	static RSString colon = RSString.createRSString(":");
	static int coordCounter = 0;
	static int activeYellowboxId = -1;
	static int pooledBuffer2Len = 0;
	static RSString loadingTextures = RSString
			.createRSString("Loading textures )2 ");
	static GraphicsBuffer rightTorchGraphicsBuffer;
	static int[] compassSource;
	static int changePortsTimer = 0;
	static RSString startingGameEngine = RSString
			.createRSString("Starting game engine)3)3)3");
	static int[] shakingCycle = new int[5];
	static int menuHeight;
	static Cache spriteCache = new Cache(200);
	static GraphicsBuffer someLeftSquare;
	static int lookAtX;
	public static int cameraPitch = 128;
	static Sprite[] hintIcon;
	static int musicVolume3;
	static RSString whi = RSString.createRSString("@whi@");
	static int hintIconID = 0;
	static int itemSelectedId;
	static RSString headiconshintString = RSString
			.createRSString("headicons_hint");
	static int[] spritePallete;
	static int[] spriteOffsetY;
	static int loginScreenState = 0;
	static RSString yellow = RSString.createRSString("yellow:");
	static RSString percent1 = RSString.createRSString("(U1");
	static RSString cr2 = RSString.createRSString("@cr2@");
	static RSString purple = RSString.createRSString("purple:");
	static int privilege = 0;
	static boolean lowMem = false;
	static RSString addFriend = RSString.createRSString("Add friend");
	static IndexedSprite[] mapScene;
	static RSString q8full = RSString.createRSString("q8_full");
	static GraphicsBuffer backvmid1Buffer;
	static RSString moreOptions = RSString.createRSString(" more options");
	static int sizeX;
	static int nextRuneTime = 0;
	static int localNpcCount = 0;
	static int loginScreenCursorPos = 0;
	static Sprite[] hitMarks;
	static int activeTabArea = -1;
	static RSString goBackToTheMainRunescapeWebpage = RSString
			.createRSString("go back to the main RuneScape webpage");
	static int logoutTimer = 0;
	static int anInt1361 = 0;
	static int[] mapFunctionsX = new int[1000];
	static Cache aClass19_2186 = new Cache(30);
	static RSString loadedTextures = RSString.createRSString("Loaded textures");
	static CRC32 aCRC32_2190 = new CRC32();
	static RSString pleaseTryUsingADifferentWorld = RSString
			.createRSString("Please try using a different world)3");
	static int focusedItemInterface = 0;
	static int anInt2200 = 0;
	public static int[][] tileBrightness;
	static int anInt1401 = 256;
	static RSByteBuffer[] playerAppearanceBuffers = new RSByteBuffer[2048];
	static int gameState = 0;
	static IndexedSprite redStone2_4;
	static int anInt1408;
	static int itemSelectedInterface;
	static RSString noclip = RSString.createRSString("::noclip");
	static Class aClass1418;
	static RSString noReplyFromLoginServer = RSString
			.createRSString("No reply from loginserver)3");
	static RSString mapmarkerString = RSString.createRSString("mapmarker");
	static FileSystem objectModelsFetcher;
	static RSString newUser = RSString.createRSString("New User");
	static RSString green = RSString.createRSString("green:");
	static RSString huffmanString = RSString.createRSString("huffman");
	static RSString loadingFriend = RSString
			.createRSString("Loading friend list");
	static int[] landscapeSource;
	static int anInt262 = 0;
	static RSString loadedTitleScreen = RSString
			.createRSString("Loaded title screen");
	static IndexedSprite[] runes;
	static long processStopTime = 0L;
	static RSString chooseOption = RSString.createRSString("Choose Option");
	static Cache playerAppearanceCache = new Cache(260);
	static int[] menuActionCmd3 = new int[500];
	static int[][][] anIntArrayArrayArray1601 = new int[4][13][13];
	static boolean aBoolean1603 = false;
	static Canvas canvas;
	static BigInteger RSA_MODULUS = (new BigInteger(
			"112476715609010506333544582789951079694464079089779997708869952380197908069264273060749575244909287122179390486055179365542429463509950040986579742677704164843130333891948061809824526502441868662727568372987929521452362973138541701764943826433727682251854628903169431090322285292150558553654700605023820606519"));
	static int oldRegionY;
	static RSString colon0 = RSString.createRSString(":0");
	static boolean menuOpen = false;
	static RSString backRight2 = RSString.createRSString("backright2");
	static int[] groundSaturations;
	static int lookAtSpeed;
	static RSString p12full = RSString.createRSString("p12_full");
	static GraphicsBuffer loverSideIconsBuffer;
	static RSString aClass63_136 = RSString.createRSString("0(U");
	static RSString space = RSString.createRSString(" ");
	static RSString walkHere = RSString.createRSString("Walk here");
	static int anInt139;
	static RSString hide = RSString.createRSString("Hide");
	static RSString backvmid2 = RSString.createRSString("backvmid2");
	static int anInt142;
	static int[] fullGameScreenOffsets;
	static RSString unableToConnect = RSString
			.createRSString("Unable to connect)3");
	static int playerIndex = -1;
	static RSString tryAgainInSixtySecs = RSString
			.createRSString("Try again in 60 secs)3)3)3");
	static int pooledBuffer3Len = 0;
	static RSString publicChat = RSString.createRSString("Public chat");
	static RSString titleJpg = RSString.createRSString("title)3jpg");
	static int greenColorTimer = 0;
	static RSString loadedUpdateList = RSString
			.createRSString("Loaded update list");
	static RSString enterNameOfPlayerToAddToList = RSString
			.createRSString("Enter name of player to add to list");
	static RSString backLeft2 = RSString.createRSString("backleft2");
	static SeekableFile cacheFile;
	static int modelFetchCount = 0;
	static boolean awtFocus;
	static int[] playerIndices = new int[2048];
	static boolean hiddenButtonTest = false;
	static RSString walkableChatboxString = null;
	static RSString validCharacters = RSString
			.createRSString("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789(Q(R\0341(T(U^(V(Z(X(Y)2_=)0[*U]*W;:(W@(S*X)1<)3>)4?*6*V ");
	static Sprite[] mapFunctionsSprite = new Sprite[1000];
	static Cache itemCache = new Cache(64);
	static boolean windowFocused = true;
	static int[] anIntArray992 = { 1, 2, 4, 8 };
	static RSString yString = RSString.createRSString("(Y");
	static boolean loopMusic;
	static volatile int currenMouseAction2 = 0;
	static RSString examine = RSString.createRSString("Examine");
	static int anInt1744 = 0;
	static int cameraPacketDelay = 0;
	static long lastlastClickTime = 0L;
	static int privateChatMode = 0;
	public static int[] currentMapFiles;
	static int hintOffset = 0;
	static RSString pleaseRemove = RSString.createRSString("Please remove ");
	static int lastMouseY;
	static int musicFileID;
	static RSString duelReq = RSString.createRSString(":duelreq:");
	static RSString compassString = RSString.createRSString("compass");
	static RSString toCreateANewAccountYouNeedTo = RSString
			.createRSString("To create a new account you need to");
	static RSString star = RSString.createRSString("(Z");
	static RSString pleaseWaitAttemptingToReestablish = RSString
			.createRSString("Please wait )2 attempting to reestablish");
	static boolean tabAreaAltered = false;
	static int[] menuActionCmd2 = new int[500];
	static int anInt1205 = 0;
	static Model[] modelBuffer1 = new Model[4];
	static Model[] modelBuffer2 = new Model[4];
	public static CacheFileWorker textureWorker;
	static int spriteTrimHeight;
	static int selectedSpellInterface;
	static int[] groundsLigtness;
	static int lookAtHeight;
	static RSString preparedVisibilityMap = RSString
			.createRSString("Prepared visibility map");
	static int anInt1465 = 0;
	static int unwalkableChatboxId = -1;
	static RSString clickToContinue = RSString
			.createRSString("Click to continue");
	static GraphicsBuffer backLeft2Buffer;
	static int activeInterfaceType = 0;
	static int[] variousSettings = new int[2000];
	static int activeChatbox = -1;
	static Cache sequenceCache = new Cache(64);
	static RSString[] chatNames = new RSString[100];
	static int failAttemps = 0;
	static RSString weSuspectSomeoneKnowsYourPassword = RSString
			.createRSString("We suspect someone knows your password)3");
	static int[] anIntArray1637;
	static int[] anIntArray1636 = new int[5];
	public static SceneGraph sceneGraph;
	static int gameLoopCount;
	static int[] bitMask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511,
			1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143,
			524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431,
			67108863, 134217727, 268435455, 536870911, 1073741823, 2147483647,
			-1 };
	static int dragFrom = 0;
	static HashTable aClass40_1630 = new HashTable(32);
	static int[] entityUpdateIndices = new int[2048];
	static boolean spriteIsNull = false;
	static RSString titleBoxString = RSString.createRSString("titlebox");
	static RSString spaceCyan = RSString.createRSString(" @cya@");
	static Deque updateServerList = new Deque();
	static RSString loadedInterfaces = RSString
			.createRSString("Loaded interfaces");
	static SeekableFile crcFile;
	static Deque stillGraphicList = new Deque();
	static int anInt960 = 0;
	static RSString newLine = RSString.createRSString("*6n");
	static RSString l = RSString.createRSString("l");
	static int renderX;
	static int totalItemResults = 0;
	static int waitBeforeLogin = 0;
	static FileSystem iKitFetcher;
	static Deque customObjectSpawns = new Deque();
	static RSString whiWithMoreShitInIt = RSString.createRSString(" )2> @whi@");
	static IndexedSprite chatBack;
	static RSString spacelre = RSString.createRSString(" @lre@");
	static int[] anIntArray1093;
	public static int modeWhat = 0;
	static boolean[] playerActionsOnTop = new boolean[5];
	static RSString loginAttempsExceeded = RSString
			.createRSString("Login attempts exceeded)3");
	static RSString percent5 = RSString.createRSString("(U5");
	static int revision;
	static RSString gr2 = RSString.createRSString("@gr2@");
	public static CacheFileWorker spriteWorker;
	static Sprite[] mapFunction;
	static boolean errorPinging = false;
	static RSString scapeMain = RSString.createRSString("scape main");
	static IndexedSprite backhMid1;
	static RSString usernameText = RSString.createRSString("Username: ");
	static RSString friends = RSString.createRSString("Friends");
	static int[] gamescreenAreaOffsets;
	static CRC32 aCRC32_377 = new CRC32();
	static int lookAtY;
	static RSFont boldFont;
	static CacheFileWorker cacheIndex0;
	static int[] anIntArray385 = new int[] { 12800, 12800, 12800, 12800, 12800,
			12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800,
			12800, 12800 };
	static int thirdOpcode = 0;
	static int[][] via = new int[104][104];
	static volatile boolean redrawCanvas = false;
	static Sprite[] mapMarker;
	static int oldRegionX;
	static RSString connectingUpdateServer = RSString
			.createRSString("Connecting to update server");
	static int searchScroll = 0;
	static int[] anIntArray396;
	static int flagY = 0;
	static RSString take = RSString.createRSString("Take");
	static Font loadingFont;
	static IndexedSprite[] modIcons;
	static GraphicsBuffer chatButtonBuffer;
	static IndexedSprite[] scrollBar;
	static int latency = 0;
	static boolean chatPanelRedrawn = false;
	static int anInt155 = 0;
	static int[] anIntArray157;
	static RSString level = RSString.createRSString("level)2");
	static byte encryption;
	static boolean aBoolean159;
	static int tabID = 3;
	static int[] anIntArray162 = new int[] { 0, -1, 0, 1 };
	static RSString invalidLoginServerRequested = RSString
			.createRSString("Invalid loginserver requested)3");
	static Cache npcDefCache = new Cache(64);
	static int menuActionCount = 0;
	static int unwalkableInterfaceId = -1;
	static int anInt125 = 0;
	static int itemSelectedSlot;
	static int lookFromY;
	static BufferedRequest aClass3_Sub3_Sub13_128;
	static int[] anIntArray130 = new int[2000];
	static RSString preparedSoundEngine = RSString
			.createRSString("Prepared sound engine");
	static RSString backhmid2 = RSString.createRSString("backhmid2");
	static int lastCoordX = 0;
	static RSString chalReq = RSString.createRSString(":chalreq:");
	static RSString hiddenButtonTestString = RSString
			.createRSString("::hiddenbuttontest");
	static int pingTimer = 0;
	static FileSystem interfaceModelFetcher;
	static IndexedSprite redStone3_2;
	static long nameHash;
	static RSString percent4 = RSString.createRSString("(U4");
	static RSString pleaseSubscribeOrUseADifferentWorld = RSString
			.createRSString("Please subscribe)1 or use a different world)3");
	static int sleepModifier1 = 1;
	static RSString login = RSString.createRSString("Login");
	static int textCount = 0;
	static Cache spotAnimModelCache = new Cache(30);
	static RSString slide = RSString.createRSString("slide:");
	static FileSystem musicFetcher;
	static RSString sideIconsString = RSString.createRSString("sideicons");
	static int chatboxScroller = 78;
	static int[] anIntArray2160;
	static int soundCount = 0;
	static RSString redColon = RSString.createRSString("red:");
	static Deque aClass60_2164 = new Deque();
	static RSString passwordText = RSString.createRSString("Password: ");
	static RSString mapfunctionString = RSString.createRSString("mapfunction");
	static RSString wave = RSString.createRSString("wave:");
	static RSString message = RSString.createRSString("Message");
	static RSString loadingSprites = RSString
			.createRSString("Loading sprites )2 ");
	static int timoutTimer = 0;
	static int[] keyCodeCache = new int[128];
	static int[] friendsWorlds = new int[200];
	static int crossIndex = 0;
	static RSSocket js5Connection;
	static IndexedSprite redStone1_2;
	static int[] packetSizes = new int[] { 0, 0, 8, 0, 0, 0, -2, 0, 0, 0, 4, 6,
			0, 6, 0, 0, 0, -2, 4, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 4, 0, 2, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, -1, 4, 6,
			6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, -2, 5, 0,
			15, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 4, 0, 0, 0, 6, 0, 0, 0,
			-1, 0, 0, 1, 0, -2, 8, 10, 0, 0, 0, 0, 0, 0, 14, 0, 2, -2, 0, 0, 2,
			0, 0, 6, 0, 1, 0, 0, 6, 0, 0, 0, 5, 0, 0, 1, 4, 0, 0, 1, 0, 3, 2,
			0, 0, 0, 0, -2, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 5, -2, 3, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0,
			0, 2, 0, 2, 0, 0, 0, 2, 0, 3, 0, 0, 0, -1, 8, 0, 0, -2, 0, 0, 0, 0,
			0, 6, 2, 0, 0, 0, 0, 0, 6, 0, 0, 6, 3, 0, 0, 0, 0, 6, 0, 7, 0, 0,
			2, 6, 1, -2 };
	static byte[][] pooledBuffer2 = new byte[250][];
	static RSString unexpectedloginserverresponse = RSString
			.createRSString("Unexpected loginserver response)3");
	static int[] menuActionIndex = new int[500];
	static Sprite rightFlameSprite;
	static GraphicsBuffer someRightSquare;
	public static byte[][][] overlayIds;
	static int[][] characterColors = new int[][] {
			{ 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983,
					54193 },
			{ 8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153,
					56621, 4783, 1341, 16578, 35003, 25239 },
			{ 25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094,
					10153, 56621, 4783, 1341, 16578, 35003 },
			{ 4626, 11146, 6439, 12, 4758, 10270 },
			{ 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } };
	static int anInt884 = 0;
	static RSString errorTest = RSString.createRSString("::errortest");
	static Player[] localPlayers = new Player[2048];
	static RSString aClass63_895 = RSString.createRSString("::");
	static int[] anIntArray897 = { 1, 0, -1, 0 };
	static int gamescreenYellowBox = -1;
	static RSString hidden = RSString.createRSString("Hidden");
	static FileSystem spotAnimationFetcher;
	static int[] bitMasks = new int[32];
	static Sprite[] prayerIcon;
	public static CollisionMap[] collisionMaps = new CollisionMap[5];
	static int localEntityCount = 0;
	static PlayerAppearance playerAppearance = new PlayerAppearance();
	static int menuWidth;
	static int anInt720 = 0;
	static RSString scroll = RSString.createRSString("scroll:");
	static RSString dropClient = RSString.createRSString("::clientdrop");
	static boolean redrawTabArea = false;
	static int crossY = 0;
	static RSString yourProfileWillBeTransferredIn = RSString
			.createRSString("Your profile will be transferred in:");
	static int multiIcon = 0;
	static boolean objectLowMem = false;
	static int[] landscapeDestination;
	static boolean fetchMusic = false;
	static RSString pleaseTryAgain = RSString
			.createRSString("Please try again)3");
	static RSString aClass63_736 = RSString.createRSString(" @whi@(X");
	static RSString loggedOut = RSString.createRSString(" has logged out)3");
	static int ignoreCount = 0;
	static RSString systemUpdateIn = RSString
			.createRSString("System update in: ");
	static int[] pressedKeyCharCache = new int[128];
	static RSString fpsOff = RSString.createRSString("::fpsoff");
	static RSString enterName = RSString.createRSString("Enter name:");
	static RSString name = RSString.createRSString("");
	static RSString textInput = RSString.createRSString("");
	static RSString reportAbuseInput = RSString.createRSString("");
	static RSString titleboxLine1 = RSString.createRSString("");
	static RSString password = RSString.createRSString("");
	static RSString privateChatPromptInput = RSString.createRSString("");
	static RSString titleboxLine3 = RSString.createRSString("");
	static RSString tempInput = RSString.createRSString("");
	static RSString loadingText = RSString.createRSString("");
	static RSString inputMessage = RSString.createRSString("");
	static RSString inputText = RSString.createRSString("");
	static RSString titleboxLine2 = RSString.createRSString("");
	static Canvas objRenderCanvas;

	public Client(JPanel panel) {
		this.clientFrame = panel;
	}

	final void resetFields() {
		reset();
		RSFont.reset();
		SceneGraph.reset();
		Model.reset();
		CompoundTile.reset();
		Rasterizer.reset();
		Graphics2D.resetPixels();
		Texture.reset();
		BZip2Decompressor.reset();
		Animation.reset();
	}

	public static void reset() {
		or1 = null;
		pleaseSubscribeOrUseADifferentWorld = null;
		login = null;
		noclip = null;
		loggedOut = null;
		yourProfileWillBeTransferredIn = null;
		pressedKeyCharCache = null;
		landscapeDestination = null;
		aClass63_736 = null;
		pleaseTryAgain = null;
		scroll = null;
		dropClient = null;
		systemUpdateIn = null;
		playerAppearance = null;
		enterName = null;
		localPlayers = null;
		collisionMaps = null;
		fpsOff = null;
		inputMessage = null;
		tempInput = null;
		password = null;
		titleboxLine2 = null;
		titleboxLine1 = null;
		textInput = null;
		titleboxLine3 = null;
		name = null;
		inputText = null;
		reportAbuseInput = null;
		privateChatPromptInput = null;
		loadingText = null;
		prayerIcon = null;
		anIntArray897 = null;
		bitMasks = null;
		errorTest = null;
		spotAnimationFetcher = null;
		hidden = null;
		aClass63_895 = null;
		aCRC32_377 = null;
		passwordText = null;
		redColon = null;
		friendsWorlds = null;
		unexpectedloginserverresponse = null;
		loadingSprites = null;
		menuActionIndex = null;
		characterColors = null;
		rightFlameSprite = null;
		redStone1_2 = null;
		someRightSquare = null;
		keyCodeCache = null;
		overlayIds = null;
		js5Connection = null;
		packetSizes = null;
		message = null;
		pooledBuffer2 = null;
		anIntArray2160 = null;
		musicFetcher = null;
		mapfunctionString = null;
		aClass60_2164 = null;
		spotAnimModelCache = null;
		slide = null;
		sideIconsString = null;
		wave = null;
		cacheIndex0 = null;
		aClass63_1727 = null;
		redstone3 = null;
		percent4 = null;
		fullscreenGraphics = null;
		regularFont = null;
		backRight2Buffer = null;
		cacheIndex11 = null;
		worldConnection = null;
		bigX = null;
		chalReq = null;
		backhmid2 = null;
		redStone3_2 = null;
		interfaceModelFetcher = null;
		hiddenButtonTestString = null;
		loadingFont = null;
		mapMarker = null;
		take = null;
		anIntArray396 = null;
		connectingUpdateServer = null;
		anIntArray385 = null;
		via = null;
		aClass3_Sub3_Sub13_128 = null;
		preparedSoundEngine = null;
		npcDefCache = null;
		anIntArray130 = null;
		anIntArray162 = null;
		chatButtonBuffer = null;
		scrollBar = null;
		modIcons = null;
		level = null;
		invalidLoginServerRequested = null;
		anIntArray157 = null;
		boldFont = null;
		scapeMain = null;
		backhMid1 = null;
		spriteWorker = null;
		gamescreenAreaOffsets = null;
		gr2 = null;
		usernameText = null;
		mapFunction = null;
		friends = null;
		loadedInterfaces = null;
		titleBoxString = null;
		l = null;
		newLine = null;
		entityUpdateIndices = null;
		stillGraphicList = null;
		crcFile = null;
		spaceCyan = null;
		updateServerList = null;
		whiWithMoreShitInIt = null;
		anIntArray1093 = null;
		percent5 = null;
		loginAttempsExceeded = null;
		spacelre = null;
		playerActionsOnTop = null;
		iKitFetcher = null;
		customObjectSpawns = null;
		chatBack = null;
		redStone2_4 = null;
		tileBrightness = null;
		preparedVisibilityMap = null;
		clickToContinue = null;
		backLeft2Buffer = null;
		aClass40_1630 = null;
		chatNames = null;
		bitMask = null;
		sequenceCache = null;
		anIntArray1636 = null;
		anIntArray1637 = null;
		weSuspectSomeoneKnowsYourPassword = null;
		sceneGraph = null;
		variousSettings = null;
		newUser = null;
		groundsLigtness = null;
		pleaseWaitAttemptingToReestablish = null;
		star = null;
		toCreateANewAccountYouNeedTo = null;
		compassString = null;
		menuActionCmd2 = null;
		textureWorker = null;
		modelBuffer1 = null;
		modelBuffer2 = null;
		runes = null;
		chooseOption = null;
		canvas = null;
		anIntArrayArrayArray1601 = null;
		menuActionCmd3 = null;
		playerAppearanceCache = null;
		titleJpg = null;
		fullGameScreenOffsets = null;
		unableToConnect = null;
		publicChat = null;
		pleaseRemove = null;
		currentMapFiles = null;
		duelReq = null;
		tryAgainInSixtySecs = null;
		yString = null;
		anIntArray992 = null;
		walkableChatboxString = null;
		validCharacters = null;
		examine = null;
		itemCache = null;
		mapFunctionsSprite = null;
		loadedTitleScreen = null;
		green = null;
		huffmanString = null;
		colon0 = null;
		RSA_MODULUS = null;
		backRight2 = null;
		aClass63_136 = null;
		p12full = null;
		backvmid2 = null;
		loadedUpdateList = null;
		cacheFile = null;
		playerIndices = null;
		enterNameOfPlayerToAddToList = null;
		backLeft2 = null;
		loverSideIconsBuffer = null;
		hide = null;
		walkHere = null;
		space = null;
		groundSaturations = null;
		loadingFriend = null;
		landscapeSource = null;
		objectModelsFetcher = null;
		noReplyFromLoginServer = null;
		mapmarkerString = null;
		playerAppearanceBuffers = null;
		loadedTextures = null;
		aClass19_2186 = null;
		pleaseTryUsingADifferentWorld = null;
		aCRC32_2190 = null;
		anIntArray9 = null;
		mapsceneString = null;
		playerActions = null;
		soundIds = null;
		anIntArray7 = null;
		spaceYel = null;
		world = null;
		referenceCache = null;
		tabInterfaceIDs = null;
		goBackToTheMainRunescapeWebpage = null;
		moreOptions = null;
		mapFunctionsX = null;
		hitMarks = null;
		underScore = null;
		addFriend = null;
		backvmid1Buffer = null;
		q8full = null;
		mapScene = null;
		rightTorchGraphicsBuffer = null;
		compassSource = null;
		whi = null;
		headiconshintString = null;
		hintIcon = null;
		cr2 = null;
		yellow = null;
		percent1 = null;
		purple = null;
		spriteCache = null;
		loadingTextures = null;
		someLeftSquare = null;
		shakingCycle = null;
		startingGameEngine = null;
		fromYourIgnoreListFirst = null;
		offline = null;
		connectingToServer = null;
		underlayShapes = null;
		classCheckCache = null;
		heightMod = null;
		shaking = null;
		chatAreaOffsets = null;
		or2 = null;
		enterNameOfPlayerToDeleteFromList = null;
		loadingFontMetrics = null;
		loadingPleaseWait = null;
		groundHues = null;
		mapdotsString = null;
		pressChangeYourPasswordOnFrontPage = null;
		acceptChallenge = null;
		privateChat = null;
		currentExp = null;
		gre = null;
		tooManyConnectionsFromYourAddress = null;
		updateServerConnection = null;
		varpCache = null;
		currentLandscapeFiles = null;
		resultNames = null;
		underlayFetcher = null;
		colonWithSpace = null;
		pressedKeyCodeCache = null;
		pleaseReloadThisPage = null;
		welcomeToRunescape = null;
		indexFile = null;
		menuActionOpcode = null;
		percent2 = null;
		mapEdge = null;
		itemSpriteCache = null;
		redStone2 = null;
		chatback = null;
		smallFont = null;
		enterNameOfFriendToDeleteFromList = null;
		redstone1 = null;
		titleButtonString = null;
		aClass3_Sub12_1667 = null;
		spellTooltip = null;
		xpForLevel = null;
		unableToFind = null;
		colonSpace = null;
		cost = null;
		mapedgeString = null;
		enterObjectName = null;
		buttonNearTheTopOfThatPage = null;
		tileSettings = null;
		modIconsString = null;
		aClass3_Sub12_2087 = null;
		fpsOn = null;
		interfaceSpriteFetcher = null;
		mouseHandler = null;
		x = null;
		loadedWorldPack = null;
		memory = null;
		tileHeight = null;
		dot = null;
		million2 = null;
		backTop1 = null;
		createFreeAccount = null;
		aClass40_14 = null;
		pooledBuffer1 = null;
		chatPanelreDrawn = null;
		arrowLre = null;
		aByteArrayArrayArray12 = null;
		sceneGraphTypes = null;
		shakingPitchMod = null;
		gameSignlink = null;
		KeyboardHandler.keyCodes = null;
		redStone3 = null;
		iKitModelFetcher = null;
		skullIcon = null;
		localNpcIndices = null;
		youHaveOnlyJustLeftAnotherWorld = null;
		cancel = null;
		fancyFont = null;
		million = null;
		toPlayOnThisWorldMoveToAFreeAreaFirst = null;
		tradeCompete = null;
		loadingIgnore = null;
		backvmid1 = null;
		pleaseShortenSearch = null;
		selectedItemName = null;
		currentRegions = null;
		attack = null;
		animationCache = null;
		objectFetcher = null;
		spritePallete = null;
		overlayCache = null;
		backRight1Buffer = null;
		fonts = null;
		gr1 = null;
		b12full = null;
		varbitFetcher = null;
		isAlreadyOnYourIgnoreList = null;
		loadingGameScreen = null;
		enterNameOfFriendToAddToList = null;
		acceptTrade = null;
		seconds = null;
		underlayCache = null;
		spaceX2 = null;
		runesString = null;
		anIntArray616 = null;
		connectionTimedOut = null;
		mapFileBuffer = null;
		spaceWhi = null;
		cacheIndex6 = null;
		gameScreenBuffer = null;
		friendsNames = null;
		cacheIndex10 = null;
		connectionLost = null;
		anIntArray2057 = null;
		anIntArray574 = null;
		backBase1 = null;
		reportAbuse = null;
		anIntArrayArray429 = null;
		muteOptionOff = null;
		anIntArrayArrayArray438 = null;
		from = null;
		classic = null;
		wishesDuel = null;
		errorConnectingToServer = null;
		invalidUsernameOrPassword = null;
		spaceXspace = null;
		yelStarV = null;
		sequenceFetcher = null;
		multiIconSprite = null;
		wave2 = null;
		connectingToFriend = null;
		invBack = null;
		interfaceLoaded = null;
		chatTypes = null;
		unexpectedServerResponse = null;
		cyan = null;
		spritePixels = null;
		enterMount = null;
		white = null;
		spaceX = null;
		fromYourFriendListFirst = null;
		glow2 = null;
		heldKeys = null;
		menuActionName = null;
		aClass40_1146 = null;
		errorLoadingYourProfile = null;
		nothing = null;
		pleaseUseADifferentWorld = null;
		backbase2 = null;
		mapback = null;
		anIntArray679 = null;
		yel = null;
		friendList = null;
		errorSignlink = null;
		titleButton = null;
		overlayFetcher = null;
		leftTorchGraphicsBuffer = null;
		loginServerOffline = null;
		itemModelCache = null;
		spaceArrow = null;
		loadingConfigs = null;
		fpsCache = null;
		mapFunctionsY = null;
		yourAccountIsAlreadyLoggedIn = null;
		use = null;
		iKitCache = null;
		objectDefCache = null;
		invback = null;
		maxStats = null;
		npcModelFetcher = null;
		headColors = null;
		underTitleBoxGraphicsBuffer = null;
		or3 = null;
		muteOptionOn = null;
		iconsreDrawn = null;
		drop = null;
		loadingWorldpack = null;
		flash1 = null;
		soundTypes = null;
		spaceArrowCya = null;
		validChars = null;
		membersArea = null;
		aClass30_2033 = null;
		projectileList = null;
		overlaymultiwayString = null;
		runescapeHasBeenUpdated = null;
		anObject821 = null;
		tabAreaBuffer = null;
		wishesTrade = null;
		continueString = null;
		cr1 = null;
		npcModelCache = null;
		skinsFetcher = null;
		to = null;
		thisServerIsBeingUpdated = null;
		localEntityIndices = null;
		percent3 = null;
		varbitCache = null;
		clientFrame = null;
		titleBox = null;
		remove = null;
		itemFetcher = null;
		aClass3_Sub12_1448 = null;
		sideIcons = null;
		redstone2 = null;
		cacheIndex7 = null;
		loadedFonts = null;
		pleaseWait = null;
		loginLimitExceeded = null;
		asItWasUsedToBreakOurRules = null;
		leftFlameSprite = null;
		dnsNode = null;
		shake = null;
		redStone1_4 = null;
		alreadyInFlist = null;
		backvmid3 = null;
		rightBracketPercent = null;
		messageCounterArray = null;
		aClass18_Sub1Array745 = null;
		thisWorldIsFull = null;
		crossString = null;
		skill = null;
		rightFlameGraphicsBuffer = null;
		anIntArray743 = null;
		backvmid3Buffer = null;
		percentString = null;
		skeletonFetcher = null;
		hundredPercent = null;
		youNeedAMembersAccountToLogin = null;
		anIntArray1238 = null;
		flash2 = null;
		pooledBuffer3 = null;
		mapDot = null;
		lre = null;
		compassDestination = null;
		huffman = null;
		cya = null;
		landScapeAsSprite = null;
		upperSideIconsBuffer = null;
		scrollbarString = null;
		leftFlameGraphicsBuffer = null;
		backhmid2Buffer = null;
		loadingTitleScreen = null;
		noResponseFromServer = null;
		backBase2 = null;
		accountLockedAsWeSuspectItHasBeenStolen = null;
		enterYourUsernamePassowrd = null;
		compass = null;
		backLeft1Buffer = null;
		js5ConnectionNode = null;
		localNpcs = null;
		skillEnabled = null;
		membersObject = null;
		crosses = null;
		thousand = null;
		myPlayer = null;
		loadedInputHandler = null;
		chatBuffer = null;
		thousand2 = null;
		chatColors = null;
		spaceGre = null;
		redStone1_3 = null;
		loadingInterfaces = null;
		loadingFonts = null;
		malformedLoginPacket = null;
		backbase1 = null;
		anIntArray558 = null;
		gameLoopTime = null;
		pleaseWait1MinuteAndTryAgain = null;
		modelCache = null;
		spriteOffsetX = null;
		bigY = null;
		select = null;
		cacheIndex3 = null;
		pressRecoverALockedAccount = null;
		gr3 = null;
		yourAccountHasBeenDisabled = null;
		loggedIn = null;
		soundDelays = null;
		xtea = null;
		minimapBuffer = null;
		YourIgnoreListIsFullMaxOf100Hit = null;
		cabbage = null;
		pleaseContactCustomerSupport = null;
		aClass19_638 = null;
		sidePanelreDrawn = null;
		underlayIds = null;
		headiconsprayerSprite = null;
		backhmid1 = null;
		musicBuffer = null;
		worldConnection2 = null;
		checkingForUpdates = null;
		backLeft1 = null;
		thisComputersAddressHasBeenBlocked = null;
		loadedConfigs = null;
		loadedSprites = null;
		off = null;
		backvmid2Buffer = null;
		glow3 = null;
		existingUser = null;
		cacheIndex4 = null;
		red = null;
		tabAreaOffsets = null;
		rsStringCache = null;
		colon = null;
		currentStats = null;
		flash3 = null;
		spriteHeight = null;
		pleaseCheckYourMessageCentreForDetails = null;
		badSession = null;
		ok = null;
		nullString = null;
		redStone1 = null;
		musicCache = null;
		headiconspkString = null;
		percentDns = null;
		loadedGameScreen = null;
		couldNotCompleteLogin = null;
		mapBack = null;
		redStone2_3 = null;
		p11full = null;
		varpFetcher = null;
		resultIds = null;
		spotAnimCache = null;
		worldConnectionNode = null;
		redStone2_2 = null;
		andChooseTheCreateAccount = null;
		enterMessageToSendTo = null;
		groundAlphas = null;
		friendlistIsFull = null;
		interfaceFetcher = null;
		whi2 = null;
		spriteWidth = null;
		hitmarksString = null;
		buffer = null;
		runescapeIsLoadingPleaseWait = null;
		cacheIndex2 = null;
		addIgnore = null;
		itemModelFetcher = null;
		textSpokenColor = null;
		textSpokenY = null;
		textSpokenWidth = null;
		glow1 = null;
		textSpokenX = null;
		tradeReq = null;
		k = null;
		textSpokenMessage = null;
		keyboardHandler = null;
		logoGraphicsBuffer = null;
		on = null;
		aClass40_1335 = null;
		npcFetcher = null;
		chatMessages = null;
		landscapeFileBuffer = null;
		titleBoxGraphicsBuffer = null;
		logo = null;
		flameRotation = null;
		aClass63_324 = null;
		cacheIndex1 = null;
		chatboxAreaBuffer = null;
		m = null;
		textSpokenHeight = null;
		cacheIndex5 = null;
		ignoreList = null;
		textSpokenCycle = null;
		closeInterface = null;
		screenTimer = null;
		fpsString = null;
		shakingPower = null;
		spotAnimationModelFetcher = null;
		close = null;
		textSpokenEffect = null;
		backTop1Buffer = null;
		spriteOffsetY = null;
		backRight1 = null;
		RSA_EXPONENT = null;
		aClass19_178 = null;
		underlayRotations = null;
	}

	final void gameLoop() {
		Client.loopCycle++;
		//handleUpdateServer();//TODO useless
		Client.handleRequets();//Don't remove or white screen :|
		Client.handleKeyboard();
		Client.handleMouse();
		if (Client.gameState == 0) {
			Client.startup();
			Client.unpackMapIndex();
			Client.method635();
		}
		if (Client.gameState == 10) {
			setGameState(25);
		}
		if (Client.gameState == 25) {
			Client.rebuildMap();
		}
		if (Client.gameState == 30) {
			processMainGameLoop();
		}
	}

	final void close() {
		if (Client.worldConnection != null)
			Client.worldConnection.close();
		Client.nullKeyboard();
		Client.nullMouse();
		Client.closeUpdateServer();
		Client.method665();
		try {
			if (Client.cacheFile != null)
				Client.cacheFile.close();
			if (Client.indexFile != null) {
				for (int id = 0; id < Client.indexFile.length; id++) {
					if (Client.indexFile[id] != null)
						Client.indexFile[id].close();
				}
			}
			if (Client.crcFile != null)
				Client.crcFile.close();
		} catch (IOException ioexception) {
		}
	}

	private final void handleUpdateServer() {
		// if (Client.gameState != 1000) {
		boolean bool = Client.processUpdateServer();
		// if (!bool)
		// js5Connect();
		// }
	}

	public final void init() {
		if (validHost()) {
			Client.worldId = Integer.parseInt(getParameter("worldid"));
			Client.modeWhat = Integer.parseInt(getParameter("modewhat"));
			Client.modeWhere = Integer.parseInt(getParameter("modewhere"));
			String lowmem = getParameter("lowmem");
			if (lowmem != null && lowmem.equals("1"))
				Client.lowMem();
			else
				Client.highMem();
			String members = getParameter("members");
			if (members != null && members.equals("1"))
				Client.allowMemberItems = true;
			else
				Client.allowMemberItems = false;
			initApplet(765, 503, 414, Client.modeWhat + 32);
		}
	}

	final void processDrawing() {
		if (Client.redrawCanvas) {
			Client.removeKeyboard(Client.canvas);
			Client.removeMouse(Client.canvas);
			addCanvas();
			Client.addKeyboard(Client.canvas);
			Client.addMouse(Client.canvas);
		}
		if (Client.gameState == 0)
			Client.drawLoadingText(null, Client.loadingText,
					Client.loadingBarPercent);
		else if (Client.gameState == 25) {
			/*
			 * if (Client.loadingScreenType == 1) { if (Client.one2 <
			 * Client.mapFilesFetchCount) Client.one2 =
			 * Client.mapFilesFetchCount; int percent = (Client.one2 -
			 * Client.mapFilesFetchCount) * 50 / Client.one2;
			 * Client.drawTextOnScreen( Client.loadingPleaseWait,
			 * Client.linkRSStrings(new RSString[] { Client.x,
			 * RSString.valueOf(percent), Client.rightBracketPercent }), true);
			 * } else if (Client.loadingScreenType == 2) { if
			 * (Client.modelFetchCount > Client.one) Client.one =
			 * Client.modelFetchCount; int percent = ((Client.one -
			 * Client.modelFetchCount) * 50 / Client.one) + 50;
			 * Client.drawTextOnScreen( Client.loadingPleaseWait,
			 * Client.linkRSStrings(new RSString[] { Client.x,
			 * RSString.valueOf(percent), Client.rightBracketPercent }), true);
			 * } else Client.drawTextOnScreen(Client.loadingPleaseWait, null,
			 * false);
			 */
		} else if (Client.gameState == 30)
			Client.drawGameFrame();
		else if (Client.gameState == 40)
			Client.drawTextOnScreen(Client.connectionLost,
					Client.pleaseWaitAttemptingToReestablish, false);
		Client.scrollPosition = 0;
	}

	static final void method37(CacheFile class28, byte[] is, int i) {
		UpdateServerNode node = new UpdateServerNode();
		node.hash = (long) i;
		node.data = is;
		node.anInt1246 = 0;
		node.cache = class28;
		synchronized (Client.updateServerList) {
			Client.updateServerList.insertBack(node);
		}
		Client.method568();
	}

	final void constructSettings() {
		Client.worldPort = Client.modeWhere != 0 ? Client.worldId + 40000
				: 43594;
		Client.worldPort2 = Client.worldPort;
		Client.anInt118 = Client.modeWhere == 0 ? 443 : Client.worldId + 50000;
		Client.synchronizeKeys();
		Client.addKeyboard(Client.canvas);
		Client.addMouse(Client.canvas);
		try {
			if (Client.gameSignlink.cacheFile != null) {
				Client.cacheFile = new SeekableFile(
						Client.gameSignlink.cacheFile, 5200, 0);
				for (int indexes = 0; indexes < 12; indexes++)
					Client.indexFile[indexes] = new SeekableFile(
							Client.gameSignlink.indexFile[indexes], 6000, 0);
				Client.crcFile = new SeekableFile(Client.gameSignlink.crcFile,
						6000, 0);
				Client.referenceCache = new CacheFile(Client.cacheFile,
						Client.crcFile, 255, 500000);
				Client.gameSignlink.indexFile = null;
				Client.gameSignlink.crcFile = null;
				Client.gameSignlink.cacheFile = null;
			}
		} catch (IOException ioexception) {
			Client.cacheFile = null;
			Client.crcFile = null;
			Client.referenceCache = null;
		}
	}

	static final void synchronizeKeys() {
		if (Signlink.vendor.toLowerCase().indexOf("microsoft") != -1) {
			KeyboardHandler.keyCodes[186] = 57;
			KeyboardHandler.keyCodes[187] = 27;
			KeyboardHandler.keyCodes[188] = 71;
			KeyboardHandler.keyCodes[189] = 26;
			KeyboardHandler.keyCodes[190] = 72;
			KeyboardHandler.keyCodes[191] = 73;
			KeyboardHandler.keyCodes[192] = 58;
			KeyboardHandler.keyCodes[219] = 42;
			KeyboardHandler.keyCodes[220] = 74;
			KeyboardHandler.keyCodes[221] = 43;
			KeyboardHandler.keyCodes[222] = 59;
			KeyboardHandler.keyCodes[223] = 28;
		} else {
			KeyboardHandler.keyCodes[44] = 71;
			KeyboardHandler.keyCodes[45] = 26;
			KeyboardHandler.keyCodes[46] = 72;
			KeyboardHandler.keyCodes[47] = 73;
			KeyboardHandler.keyCodes[59] = 57;
			KeyboardHandler.keyCodes[61] = 27;
			KeyboardHandler.keyCodes[91] = 42;
			KeyboardHandler.keyCodes[92] = 74;
			KeyboardHandler.keyCodes[93] = 43;
			if (Signlink.traversalKey != null) {
				KeyboardHandler.keyCodes[192] = 28;
				KeyboardHandler.keyCodes[222] = 58;
				KeyboardHandler.keyCodes[520] = 59;
			} else {
				KeyboardHandler.keyCodes[192] = 58;
				KeyboardHandler.keyCodes[222] = 59;
			}
		}
	}

	static final void clearCache() {
		resetUnderlays();
		resetOverlays();
		resetIkit();
		resetObjects();
		resetSequence();
		resetSpotAnimations();
		resetVarbit();
		resetVarp();
		resetAppearance();
		((TextureLoader) Rasterizer.textureInterface).resetTextures();
		cacheIndex0.clear();
		cacheIndex1.clear();
		cacheIndex3.clear();
		cacheIndex4.clear();
		cacheIndex5.clear();
		cacheIndex6.clear();
		cacheIndex7.clear();
		spriteWorker.clear();
		textureWorker.clear();
		cacheIndex10.clear();
		cacheIndex11.clear();
	}

	static final int method54() {
		int i = 3;
		/*if (renderPitch < 310) {
			int i_13_ = renderX >> 7;
			int i_14_ = renderY >> 7;
			if (i_14_ < 0) // TODO: Temp fix cos -8
				i_14_ = 1;
			if (i_13_ < 0) // TODO: Temp fix cos -1
				i_13_ = 1;
			if (((tileSettings[height][i_13_][i_14_]) & 0x4) != 0)
				i = height;
			int i_15_ = playerRenderX >> 7;
			int i_16_ = playerRenderY >> 7;
			int i_17_;
			if (i_16_ <= i_14_)
				i_17_ = i_14_ - i_16_;
			else
				i_17_ = i_16_ - i_14_;
			int i_18_;
			if (i_15_ > i_13_)
				i_18_ = i_15_ - i_13_;
			else
				i_18_ = i_13_ - i_15_;
			if (i_18_ > i_17_) {
				int i_19_ = i_17_ * 65536 / i_18_;
				int i_20_ = 32768;
				while (i_13_ != i_15_) {
					if (i_13_ < i_15_)
						i_13_++;
					else if (i_15_ < i_13_)
						i_13_--;
					if (((tileSettings[height][i_13_][i_14_]) & 0x4) != 0)
						i = height;
					i_20_ += i_19_;
					if (i_20_ >= 65536) {
						if (i_14_ < i_16_)
							i_14_++;
						else if (i_14_ > i_16_)
							i_14_--;
						i_20_ -= 65536;
						if (((tileSettings[height][i_13_][i_14_]) & 0x4) != 0)
							i = height;
					}
				}
			} else {
				int i_21_ = i_18_ * 65536 / i_17_;
				int i_22_ = 32768;
				while (i_14_ != i_16_) {
					i_22_ += i_21_;
					if (i_14_ < i_16_)
						i_14_++;
					else if (i_16_ < i_14_)
						i_14_--;
					if (((tileSettings[height][i_13_][i_14_]) & 0x4) != 0)
						i = height;
					if (i_22_ >= 65536) {
						if (i_15_ <= i_13_) {
							if (i_15_ < i_13_)
								i_13_--;
						} else
							i_13_++;
						i_22_ -= 65536;
						if (((tileSettings[height][i_13_][i_14_]) & 0x4) != 0)
							i = height;
					}
				}
			}
		}*/
		if((playerRenderX >> 7) < 0)
			playerRenderX = 1;
		if((playerRenderY >> 7) < 0)
			playerRenderY = 1;
		if((playerRenderX >> 7) >= 104)
			playerRenderX = 103;
		if((playerRenderY >> 7) >= 104)
			playerRenderY = 103;
		if (((tileSettings[height][playerRenderX >> 7][playerRenderY >> 7]) & 0x4) != 0)
			i = height;
		return i;
	}

	static final void updateRegion(int height, int regionX, int regionY) {
		if (!lowMem)
			height = 0;
		if (oldRegionX != regionX || oldRegionY != regionY
				|| height != oldHeight) {
			oldRegionY = regionY;
			oldRegionX = regionX;
			oldHeight = height;
			drawTextOnScreen(loadingPleaseWait, null, false);
			int baseY = currentBaseY;
			currentBaseY = regionY * 8 - 48;
			int baseX = currentBaseX;
			int yDifference = currentBaseY - baseY;
			baseY = currentBaseY;
			currentBaseX = (regionX - 6) * 8;
			int xDifference = currentBaseX - baseX;
			baseX = currentBaseX;
			for (int index = 0; index < 16384; index++) {
				Npc npc = localNpcs[index];
				if (npc != null) {
					for (int id = 0; id < 10; id++) {
						npc.walkQueueX[id] -= xDifference;
						npc.walkQueueY[id] -= yDifference;
					}
					npc.x -= xDifference * 128;
					npc.y -= yDifference * 128;
				}
			}
			for (int index = 0; index < 2048; index++) {
				Player player = localPlayers[index];
				if (player != null) {
					for (int id = 0; id < 10; id++) {
						player.walkQueueX[id] -= xDifference;
						player.walkQueueY[id] -= yDifference;
					}
					player.x -= xDifference * 128;
					player.y -= yDifference * 128;
				}
			}
			Client.height = height;
			int i_15_ = 0;
			int i_16_ = 104;
			// myPlayer.updatePosition(localX, localY, false);
			int i_17_ = 1;
			int i_18_ = 0;
			if (0 > xDifference) {
				i_16_ = -1;
				i_15_ = 103;
				i_17_ = -1;
			}
			int i_19_ = 104;
			int i_20_ = 1;
			if (yDifference < 0) {
				i_18_ = 103;
				i_19_ = -1;
				i_20_ = -1;
			}
			for (CustomObjectSpawn spawn = (CustomObjectSpawn) customObjectSpawns
					.getFront(); spawn != null; spawn = (CustomObjectSpawn) customObjectSpawns
					.getNext()) {
				spawn.x -= xDifference;
				spawn.y -= yDifference;
				if (spawn.x < 0 || spawn.y < 0 || spawn.x >= 104
						|| spawn.y >= 104)
					spawn.unlink();
			}
			minimapHeight = -1;
			updateCamera = false;
			if (flagX != 0) {
				flagY -= yDifference;
				flagX -= xDifference;
			}
			stillGraphicList.clear();
			projectileList.clear();
		}
	}

	static final Sprite[] getMultipleSprite(FileSystem class18,
			RSString class63, RSString class63_59_) {
		int i_60_ = class18.getHash(class63_59_);
		int i_61_ = class18.getHash(class63, i_60_);
		return fetchMultiplerSprite(i_60_, class18, i_61_);
	}

	static final void method818() {
		screenTimer.reset();
		for (int i_1_ = 0; i_1_ < 32; i_1_++)
			fpsCache[i_1_] = 0L;
		for (int i_2_ = 0; i_2_ < 32; i_2_++)
			gameLoopTime[i_2_] = 0L;
		gameLoopCount = 0;
	}

	static final void method635() {
		screenTimer.start();
		for (int i_11_ = 0; i_11_ < 32; i_11_++)
			fpsCache[i_11_] = 0L;
		for (int i_12_ = 0; i_12_ < 32; i_12_++)
			gameLoopTime[i_12_] = 0L;
		gameLoopCount = 0;
	}

	static final void resetUnderlays() {
		underlayCache.clear();
	}

	public static final IndexedSprite getTexture(FileSystem fetcher, int id) {
		if (!textureExists(fetcher, id))
			return null;
		return constructIndexedSprite();
	}

	static final void resetVarbit() {
		varbitCache.clear();
	}

	static final boolean isValidKeyChar(int i) {
		if (i >= 97 && i <= 122)
			return true;
		if (i >= 65 && i <= 90)
			return true;
		if (i >= 48 && i <= 57)
			return true;
		return false;
	}

	public static final void highMem() {
		SceneGraph.lowMem = false;
		lowMem = false;
	}

	static final int method110(int i_4_, int i_5_, int i_6_) {
		int i_7_ = 256 - i_5_;
		return (((i_5_ * (i_6_ & 0xff00ff) + (i_4_ & 0xff00ff) * i_7_ & ~0xff00ff) + ((i_4_ & 0xff00)
				* i_7_ + i_5_ * (i_6_ & 0xff00) & 0xff0000)) >> 8);
	}

	static final void processMinimapClick() {
		/*if (minimapBlackout == 0 && lastMouseAction == 1) {
			int i_22_ = lastClickY - 4 - 5;
			int i_23_ = lastClickX - 550 - 25;
			if (i_23_ >= 0 && i_22_ >= 0 && i_23_ < 146 && i_22_ < 151) {
				i_22_ -= 75;
				i_23_ -= 73;
				int i_24_ = cameraYaw & 0x7ff;
				int i_25_ = Rasterizer.sineTable[i_24_];
				int i_26_ = Rasterizer.cosineTable[i_24_];
				int i_27_ = i_26_ * i_22_ - i_25_ * i_23_ >> 11;
				int i_28_ = i_25_ * i_22_ + i_23_ * i_26_ >> 11;
				int i_29_ = (i_28_ + playerRenderX >> 7);
				int i_30_ = (playerRenderY - i_27_ >> 7);
				boolean bool = doWalkTo(true, i_30_, 0, 0, 1, 0, 0, i_29_,
						myPlayer.walkQueueX[0], 0, myPlayer.walkQueueY[0]);
			}
		}*/
	}

	static final int getRotatedLandscapeChunkX(int i, int i_20_, int i_22_,
			int i_23_, int i_24_, int i_25_) {
		i_22_ &= 0x3;
		if ((i_23_ & 0x1) == 1) {
			int i_26_ = i;
			i = i_24_;
			i_24_ = i_26_;
		}
		if (i_22_ == 0)
			return i_25_;
		if (i_22_ == 1)
			return i_20_;
		if (i_22_ == 2)
			return 7 - i_25_ - (i - 1);
		return 7 - i_20_ - (i_24_ - 1);
	}

	static final void method210() {//TODO redoo this its so hardcoded XD
		//movement
		int directionMod = (cameraYaw/64) & 30;
		if(directionMod <= 2 || directionMod == 30) {
			if(heldKeys[33]) { //w
				playerRenderY += 100;
			}
			if(heldKeys[48]) { //a
				playerRenderX-=100;
			}
			if(heldKeys[49]) { //s
				playerRenderY-=100;
			}
			if(heldKeys[50]) { //d
				playerRenderX+=100;
			}
		} else if(directionMod <= 28 && directionMod >= 22) {
			if(heldKeys[33]) { //w
				//playerRenderY += 100;
				playerRenderX+=100;
			}
			if(heldKeys[48]) { //a
				playerRenderY += 100;
				//playerRenderX-=100;
			}
			if(heldKeys[49]) { //s
				playerRenderX-=100;
				//playerRenderY-=100;
			}
			if(heldKeys[50]) { //d
				playerRenderY-=100;
				//playerRenderX+=100;
			}
		} else if(directionMod <= 22 && directionMod >= 14) {
			if(heldKeys[33]) { //w
				playerRenderY -= 100;
			}
			if(heldKeys[48]) { //a
				playerRenderX += 100;
			}
			if(heldKeys[49]) { //s
				playerRenderY += 100;
			}
			if(heldKeys[50]) { //d
				playerRenderX -= 100;
			}
		} else if(directionMod <= 14 && directionMod >= 4) {
			if(heldKeys[33]) { //w
				playerRenderX -= 100;
			}
			if(heldKeys[48]) { //a
				playerRenderY -= 100;
			}
			if(heldKeys[49]) { //s
				playerRenderX += 100;
			}
			if(heldKeys[50]) { //d
				playerRenderY += 100;
			}
		}
		/*if(heldKeys[33]) { //w
			playerRenderY += 150;
		}
		if(heldKeys[48]) { //a
			playerRenderX-=150;
		}
		if(heldKeys[49]) { //s
			playerRenderY-=150;
		}
		if(heldKeys[50]) { //d
			playerRenderX+=150;
		}*/
		//end of movement
		
		if (heldKeys[96]) {
			cameraYawModifier += (-cameraYawModifier - 24) / 2;
		} else if (heldKeys[97]) {
			cameraYawModifier += (-cameraYawModifier + 24) / 2;
		} else
			cameraYawModifier /= 2;
		int i_17_ = playerRenderX >> 7;
		int i_18_ = 0;
		int i_19_ = playerRenderY >> 7;
		if (heldKeys[98]) {
			cameraPitchModifier += (-cameraPitchModifier + 12) / 2;
		} else if (heldKeys[99]) {
			cameraPitchModifier += (-cameraPitchModifier - 12) / 2;
		} else
			cameraPitchModifier /= 2;
		if (wheelRotateYaw != -1) {
			cameraYawModifier += wheelRotateYaw * 3;
			wheelRotateYaw = -1;
		}
		if (wheelRotatePitch != -1) {
			cameraPitchModifier += wheelRotatePitch * 3;
			wheelRotatePitch = -1;
		}
		cameraPitch += cameraPitchModifier / 2;
		cameraYaw += cameraYawModifier / 2 & 0x7ff;
		if(cameraPitch < 0 || cameraPitch > 2047)
			cameraPitch = 0;
		/*if (cameraPitch < 128)
			cameraPitch = 128;
		if (cameraPitch > 383)
			cameraPitch = 383;*/
		int i_20_ = getTileHeight(playerRenderX, playerRenderY, height);
		if (i_17_ > 3 && i_19_ > 3 && i_17_ < 100 && i_19_ < 100) {
			for (int i_21_ = i_17_ - 4; i_21_ <= i_17_ + 4; i_21_++) {
				for (int i_22_ = i_19_ - 4; i_19_ + 4 >= i_22_; i_22_++) {
					int i_23_ = height;
					if (i_23_ < 3
							&& ((tileSettings[1][i_21_][i_22_]) & 0x2) == 2) {
						i_23_++;
					}
					int i_24_ = i_20_ - tileHeight[i_23_][i_21_][i_22_];
					if (i_24_ > i_18_)
						i_18_ = i_24_;
				}
			}
		}
		int i_25_ = i_18_ * 192;
		if (i_25_ > 98048)
			i_25_ = 98048;
		if (i_25_ < 32768)
			i_25_ = 32768;
		if (i_25_ <= anInt960) {
			if (anInt960 > i_25_)
				anInt960 += (i_25_ - anInt960) / 80;
		} else
			anInt960 += (i_25_ - anInt960) / 24;
	}

	public static final int blendOverlayColor(int i_10_, int i_11_) {
		if(!Editor.disableTileShadow) {
			if (i_11_ == -1)
				return 12345678;
			i_10_ = (i_11_ & 0x7f) * i_10_ / 128;
			if (i_10_ >= 2) {
				if (i_10_ > 126)
					i_10_ = 126;
			} else
				i_10_ = 2;
			return (i_11_ & 0xff80) + i_10_;
		} else {
			if (i_11_ == -1)
				return 12345678;
			return i_11_;
		}
	}

	static final void throwError(Throwable throwable, String string) {
		try {
			String string_34_ = "";
			if (throwable != null) {
				string_34_ = Client.method545(throwable);
			}
			if (string != null) {
				if (throwable != null)
					string_34_ = new StringBuilder(string_34_).append(" | ")
							.toString();
				string_34_ = new StringBuilder(string_34_).append(string)
						.toString();
			}
			System.out.println("Error: " + string_34_);
			string_34_ = string_34_.replace(':', '.');
			string_34_ = string_34_.replace('@', '_');
			string_34_ = string_34_.replace('&', '_');
			string_34_ = string_34_.replace('#', '_');
			SignlinkNode class7 = (errorSignlink.method758(new URL(
					errorSignlink.thisApplet.getCodeBase(), new StringBuilder(
							"clienterror.ws?c=").append(revision).append("&u=")
							.append(nameHash).append("&v1=")
							.append(Signlink.vendor).append("&v2=")
							.append(Signlink.version).append("&e=")
							.append(string_34_).toString())));
			System.out.println(new StringBuilder("clienterror.ws?c=")
					.append(revision).append("&u=").append(nameHash)
					.append("&v1=").append(Signlink.vendor).append("&v2=")
					.append(Signlink.version).append("&e=").append(string_34_)
					.toString());
			while (class7.status == 0)
				sleep(1L);
			if (class7.status == 1) {
				DataInputStream datainputstream = (DataInputStream) class7.value;
				datainputstream.read();
				datainputstream.close();
			}
		} catch (Exception exception) {
			/* empty */
		}
	}

	static final void setTitleboxText(RSString line1, RSString line2,
			RSString line3) {
		Client.titleboxLine2 = line2;
		Client.titleboxLine1 = line1;
		Client.titleboxLine3 = line3;
	}

	static final int method117(int i_0_, int i_1_) {
		long l = (long) ((i_0_ << 16) + i_1_);
		if (aClass3_Sub3_Sub13_128 == null || l != aClass3_Sub3_Sub13_128.hash)
			return 0;
		return 1 + ((aClass3_Sub12_1448.index) * 99 / (-(aClass3_Sub3_Sub13_128.padding) + aClass3_Sub12_1448.buf.length));
	}

	static final void method483(int i, int x, int i_4_, int z, int i_7_,
			int objType, int y) {
		if (x >= 0 && y >= 0 && x <= 102 && y <= 102
				&& (!lowMem || height == z)) {
			int hash = 0;
			if (objType == 0)
				hash = sceneGraph.getWallObjectHash(x, y, z);
			if (objType == 1)
				hash = sceneGraph.getWallDecorationHash(x, y, z);
			int id = -1;
			if (objType == 2)
				hash = sceneGraph.getInteractiveObjectHash(x, y, z);
			if (objType == 3)
				hash = sceneGraph.getGroundDecorationHash(x, y, z);
			if (hash != 0) {
				id = hash >> 14 & 0x7fff;
				int i_13_ = sceneGraph.getUID(z, x, y, hash);
				int type = i_13_ & 0x1f;
				int direction = i_13_ >> 6 & 0x3;
				if (objType == 0) {
					sceneGraph.resetWallObject(x, y, z);
					ObjectDefinition objDef = ObjectDefinition.forID(id);
					if (objDef.isSolid)
						Client.collisionMaps[z].removeClipping(
								objDef.isWalkable, x, direction, type, y);
				}
				if (objType == 1)
					sceneGraph.resetWallDecoration(x, y, z);
				if (objType == 2) {
					sceneGraph.method979(x, y, z);
					ObjectDefinition objDef = ObjectDefinition.forID(id);
					if (x + (objDef.sizeX) > 103 || y + (objDef.sizeX) > 103
							|| (objDef.sizeY) + x > 103
							|| y + (objDef.sizeY) > 103)
						return;
					if (objDef.isSolid)
						Client.collisionMaps[z].removeClipping(x, y,
								objDef.sizeX, objDef.sizeY, direction,
								objDef.isWalkable);
				}
				if (objType == 3) {
					sceneGraph.resetGroundDecoration(x, y, z);
					ObjectDefinition objDef = ObjectDefinition.forID(id);
					if (objDef.isSolid && objDef.hasActions == 1)
						Client.collisionMaps[z].removeGroundObjectsClip(x, y);
				}
			}
			if (i >= 0) {
				int height = z;
				if (height < 3 && (tileSettings[1][x][y] & 0x2) == 2)
					height++;
				method193(sceneGraph, Client.collisionMaps[z], y, z, height, i,
						x, i_7_, i_4_);
			}
		}
	}
	
	public static final void method483(int x, int y, int z) {
		if (x >= 0 && y >= 0 && x <= 102 && y <= 102
				&& (!lowMem || height == z)) {
			//System.out.println("removing");
			int hash = 0;
			int objType = 0;
			if (hash == 0) {
				hash = sceneGraph.getWallObjectHash(x, y, z);
				objType = 0;
			}
			if (hash == 0) {
				hash = sceneGraph.getWallDecorationHash(x, y, z);
				objType = 1;
			}
			int id = -1;
			if (hash == 0) {
				hash = sceneGraph.getInteractiveObjectHash(x, y, z);
				objType = 2;
			}
			if (hash == 0) {
				hash = sceneGraph.getGroundDecorationHash(x, y, z);
				objType = 3;
			}
			if (hash != 0) {
				id = hash >> 14 & 0x7fff;
				int i_13_ = sceneGraph.getUID(z, x, y, hash);
				int type = i_13_ & 0x1f;
				int direction = i_13_ >> 6 & 0x3;
				//System.out.println(objType+", "+type+", "+", "+direction);
				if (objType == 0) {
					sceneGraph.resetWallObject(x, y, z);
					ObjectDefinition objDef = ObjectDefinition.forID(id);
					if (objDef.isSolid)
						Client.collisionMaps[z].removeClipping(
								objDef.isWalkable, x, direction, type, y);
				}
				if (objType == 1)
					sceneGraph.resetWallDecoration(x, y, z);
				if (objType == 2) {
					sceneGraph.method979(x, y, z);
					ObjectDefinition objDef = ObjectDefinition.forID(id);
					if (x + (objDef.sizeX) > 103 || y + (objDef.sizeX) > 103
							|| (objDef.sizeY) + x > 103
							|| y + (objDef.sizeY) > 103)
						return;
					if (objDef.isSolid)
						Client.collisionMaps[z].removeClipping(x, y,
								objDef.sizeX, objDef.sizeY, direction,
								objDef.isWalkable);
				}
				if (objType == 3) {
					sceneGraph.resetGroundDecoration(x, y, z);
					ObjectDefinition objDef = ObjectDefinition.forID(id);
					if (objDef.isSolid && objDef.hasActions == 1)
						Client.collisionMaps[z].removeGroundObjectsClip(x, y);
				}
			}
		}
	}

	static final Sprite[] constructSprites() {
		Sprite[] sprites = new Sprite[spriteLength];
		for (int id = 0; id < spriteLength; id++) {
			Sprite newSprite = sprites[id] = new Sprite();
			newSprite.trimHeight = spriteTrimHeight;
			newSprite.trimWidth = spriteTrimWidth;
			newSprite.offsetX = spriteOffsetX[id];
			newSprite.offsetY = spriteOffsetY[id];
			newSprite.width = spriteWidth[id];
			newSprite.height = spriteHeight[id];
			byte[] pixels = spritePixels[id];
			int pixelsLen = newSprite.height * newSprite.width;
			newSprite.pixels = new int[pixelsLen];
			for (int len = 0; len < pixelsLen; len++)
				newSprite.pixels[len] = spritePallete[255 & pixels[len]];
		}
		resetSprites();
		return sprites;
	}

	static final RSString to24BitInteger(int i) {
		return linkRSStrings(new RSString[] { RSString.valueOf(i >> 24 & 0xff),
				dot, RSString.valueOf((i & 0xff43bb) >> 16), dot,
				RSString.valueOf((i & 0xffb8) >> 8), dot,
				RSString.valueOf(i & 0xff) });
	}

	static final int method505(int i_0_, int i_1_) {
		int i_2_ = (method413(i_0_ - 1, i_1_ - 1) - (-method413(i_0_ + 1,
				i_1_ - 1) - method413(i_0_ - 1, i_1_ + 1) - method413(i_0_ + 1,
				i_1_ + 1)));
		int i_3_ = (method413(i_0_ - 1, i_1_) - (-method413(i_0_ + 1, i_1_)
				- method413(i_0_, i_1_ - 1) - method413(i_0_, i_1_ + 1)));
		int i_4_ = method413(i_0_, i_1_);
		return i_3_ / 8 + (i_2_ / 16 + i_4_ / 4);
	}

	static final void resetSpotAnimations() {
		spotAnimCache.clear();
		spotAnimModelCache.clear();
	}

	public static final void setGameState(int state) {
		if (gameState != state) {
			if (gameState == 0)
				resetLoadingBar();
			if (state == 20 || state == 40) {
				changePorts = 0;
				loginStage = 0;
				changePortsTimer = 0;
			}
			if (state != 20 && state != 40 && worldConnection2 != null)
				worldConnection2.close();
			if (gameState == 25 || gameState == 40) {
				initGameScreen();
				Graphics2D.resetpixels();
			}
			if (gameState == 25) {
				loadingScreenType = 0;
				mapFilesFetchCount = 0;
				modelFetchCount = 0;
				one = 1;
				one2 = 1;
			}
			if (state == 0 || state == 35) {
				resetGameframe();
				resetTitleScreen();
				if (fullscreenGraphics == null)
					fullscreenGraphics = constructGraphicsBuffer(canvas, 765,
							503);
			}
			if (state == 5 || state == 10 || state == 20) {
				fullscreenGraphics = null;
				resetGameframe();
			}
			if (state == 25 || state == 30 || state == 40) {
				fullscreenGraphics = null;
				resetTitleScreen();
				// loadGameframe(spriteWorker, canvas);
			}
			redrawGameframe = true;
			gameState = state;
		}
	}

	static final boolean hovered(int id, int type) {
		if (type == 0 && activeGameScreen == id)
			return true;
		if (type == 1 && id == activeTabArea)
			return true;
		if ((type == 2 || type == 3) && activeChatbox == id)
			return true;
		return false;
	}

	static final void getEntityScreenPos(int x, int y, int offset) {
		if (x < 128 || y < 128 || x > 13056 || y > 13056) {
			spriteDrawX = -1;
			spriteDrawY = -1;
		} else {
			int z = getTileHeight(x, y, height) - offset;
			z -= renderZ;
			x -= renderX;
			y -= renderY;
			int sineP = Model.sine[renderPitch];
			int cosineP = Model.cosine[renderPitch];
			int sizeY = Model.sine[renderYaw];
			int cosineY = Model.cosine[renderYaw];
			int calc = sizeY * y + x * cosineY >> 16;
			y = -(x * sizeY) + cosineY * y >> 16;
			x = calc;
			calc = -(sineP * y) + cosineP * z >> 16;
			y = z * sineP + cosineP * y >> 16;
			z = calc;
			if (y >= 50) {
				spriteDrawY = (z << 10) / y + 167;
				spriteDrawX = (x << 10) / y + 256;
			} else {
				spriteDrawX = -1;
				spriteDrawY = -1;
			}
		}
	}

	static final void resetObjects() {
		objectDefCache.clear();
		aClass19_178.clear();
		aClass19_638.clear();
		aClass19_2186.clear();
	}

	static final int method678(int i, int i_12_) {
		int i_13_ = method74(i_12_ + 45365, 4, i + 91923);
		int i_14_ = (method74(i_12_ + 10294, 2, i + 37821) - 128 >> 1);
		int i_15_ = i_14_ + i_13_;
		int i_16_ = method74(i_12_, 1, i) - 128 >> 2;
		int i_17_ = i_16_ + i_15_ - 128;
		i_17_ = (int) ((double) i_17_ * 0.3) + 35;
		if (i_17_ >= 10) {
			if (i_17_ > 60)
				i_17_ = 60;
		} else
			i_17_ = 10;
		return i_17_;
	}

	static final int method670(int i, int i_0_) {
		if (i > i_0_) {
			int i_2_ = i_0_;
			i_0_ = i;
			i = i_2_;
		}
		for (int i_3_; i != 0; i = i_3_) {
			i_3_ = i_0_ % i;
			i_0_ = i;
		}
		return i_0_;
	}

	static final RSFont getFont(FileSystem class18, int i_0_, int i_1_) {
		if (!spriteExists(class18, i_0_, i_1_))
			return null;
		return constructFont();
	}

	static final int method1004(int i) {
		return (int) (Math.log((double) i * 0.00390625) * 868.5889638065036 + 0.5);
	}

	public static final int method1005(int hue, int sat, int light) {
		if (light > 179)
			sat /= 2;
		if (light > 192)
			sat /= 2;
		if (light > 217)
			sat /= 2;
		if (light > 243)
			sat /= 2;
		int i_19_ = light / 2 + ((hue / 4 << 10) + (sat / 32 << 7));
		return i_19_;
	}

	public static final void method1006(int posX, CollisionMap class59, int rotation,
			int objectId, int height, SceneGraph class64, int type, int posY) {
		if (!lowMem || (tileSettings[0][posX][posY] & 0x2) != 0 || (((tileSettings[height][posX][posY]) & 0x10) == 0 && method537(height, posX, posY) == oldHeight)) {
			if (anInt1332 > height)
				anInt1332 = height;
			if(posX < 0 || posY < 0 || posX > 103 || posY > 103)
				return;
			//System.out.println("past checks");
			int i_26_ = tileHeight[height][posX][posY];
			int i_27_ = tileHeight[height][posX + 1][posY];
			int i_28_ = tileHeight[height][posX + 1][posY + 1];
			int i_29_ = tileHeight[height][posX][posY + 1];
			ObjectDefinition objectDef = ObjectDefinition.forID(objectId);
			int uid = (rotation << 6) + type;
			if (objectDef.anInt1671 == 1)
				uid += 256;
			int tileHeight = i_29_ + (i_27_ + (i_26_ + i_28_)) >> 2;
			int hash = posX + (posY << 7) + (objectId << 14) + 1073741824;
			//if (objectDef.hasActions == 0)
			//	hash -= -2147483648;
			if (type == 22) {
				if (!lowMem || objectDef.hasActions != 0 || objectDef.aBoolean1665) {
					SceneModel class3_sub3_sub3;
					if (objectDef.animationId != -1 || objectDef.childrenIDs != null)
						class3_sub3_sub3 = new AnimatedObject(objectId, 22, rotation, i_26_, i_27_, i_28_, i_29_, objectDef.animationId, true);
					else
						class3_sub3_sub3 = objectDef.method336(i_26_, i_29_, rotation, 22, i_27_, i_28_);
					class64.addGroundDecoration(height, posX, posY, tileHeight, class3_sub3_sub3, hash, uid);
					//System.out.println("here");
					if (objectDef.isSolid && objectDef.hasActions == 1 && class59 != null)
						class59.addGroundObjectsClip(posX, posY);
				}
			} else if (type == 10 || type == 11) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId == -1
						&& objectDef.childrenIDs == null)
					class3_sub3_sub3 = objectDef.method336(i_26_, i_29_,
							rotation, 10, i_27_, i_28_);
				else
					class3_sub3_sub3 = new AnimatedObject(objectId, 10,
							rotation, i_26_, i_27_, i_28_, i_29_,
							objectDef.animationId, true);
				if (class3_sub3_sub3 != null) {
					int i_33_;
					int i_34_;
					if (rotation == 1 || rotation == 3) {
						i_34_ = objectDef.sizeX;
						i_33_ = objectDef.sizeY;
					} else {
						i_33_ = objectDef.sizeX;
						i_34_ = objectDef.sizeY;
					}
					int i_35_ = 0;
					if (type == 11)
						i_35_ += 256;
					if (class64.addInteractiveObject(height, posX, posY,
							tileHeight, i_33_, i_34_, class3_sub3_sub3, i_35_,
							hash, uid)
							&& objectDef.aBoolean1666) {
						Model model;
						if (!(class3_sub3_sub3 instanceof Model))
							model = objectDef.method336(i_26_, i_29_, rotation,
									10, i_27_, i_28_);
						else
							model = (Model) class3_sub3_sub3;
						if (model != null) {
							for (int i_36_ = 0; i_36_ <= i_33_; i_36_++) {
								for (int i_37_ = 0; i_37_ <= i_34_; i_37_++) {
									int i_38_ = (model.method149() / 4);
									if (i_38_ > 30)
										i_38_ = 30;
									if (aByteArrayArrayArray12[height][posX
											+ i_36_][posY + i_37_] < i_38_)
										aByteArrayArrayArray12[height][i_36_
												+ posX][i_37_ + posY] = (byte) i_38_;
								}
							}
						}
					}
				}
				if (objectDef.isSolid && class59 != null)
					class59.addClipping(posX, posY, objectDef.sizeX,
							objectDef.sizeY, rotation, objectDef.isWalkable);
			} else if (type >= 12) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId == -1
						&& objectDef.childrenIDs == null)
					class3_sub3_sub3 = objectDef.method336(i_26_, i_29_,
							rotation, type, i_27_, i_28_);
				else
					class3_sub3_sub3 = new AnimatedObject(objectId, type,
							rotation, i_26_, i_27_, i_28_, i_29_,
							objectDef.animationId, true);
				class64.addInteractiveObject(height, posX, posY, tileHeight, 1,
						1, class3_sub3_sub3, 0, hash, uid);
				if (type >= 12 && type <= 17 && type != 13 && height > 0)
					anIntArrayArrayArray438[height][posX][posY] |= 2340;
				if (objectDef.isSolid && class59 != null)
					class59.addClipping(posX, posY, objectDef.sizeX,
							objectDef.sizeY, rotation, objectDef.isWalkable);
			} else if (type == 0) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId == -1
						&& objectDef.childrenIDs == null)
					class3_sub3_sub3 = objectDef.method336(i_26_, i_29_,
							rotation, 0, i_27_, i_28_);
				else
					class3_sub3_sub3 = new AnimatedObject(objectId, 0,
							rotation, i_26_, i_27_, i_28_, i_29_,
							objectDef.animationId, true);
				class64.addWallObject(height, posX, posY, tileHeight,
						class3_sub3_sub3, null, anIntArray992[rotation], 0,
						hash, uid);
				if (rotation == 0) {
					if (objectDef.aBoolean1666) {
						aByteArrayArrayArray12[height][posX][posY] = (byte) 50;
						aByteArrayArrayArray12[height][posX][posY + 1] = (byte) 50;
					}
					if (objectDef.aBoolean1677)
						anIntArrayArrayArray438[height][posX][posY] |= 585;
				} else if (rotation == 1) {
					if (objectDef.aBoolean1666) {
						aByteArrayArrayArray12[height][posX][(posY + 1)] = (byte) 50;
						aByteArrayArrayArray12[height][posX + 1][posY + 1] = (byte) 50;
					}
					if (objectDef.aBoolean1677)
						anIntArrayArrayArray438[height][posX][(posY + 1)] |= 1170;
				} else if (rotation == 2) {
					if (objectDef.aBoolean1666) {
						aByteArrayArrayArray12[height][posX + 1][posY] = (byte) 50;
						aByteArrayArrayArray12[height][posX + 1][posY + 1] = (byte) 50;
					}
					if (objectDef.aBoolean1677)
						anIntArrayArrayArray438[height][posX + 1][posY] |= 585;
				} else if (rotation == 3) {
					if (objectDef.aBoolean1666) {
						aByteArrayArrayArray12[height][posX][posY] = (byte) 50;
						aByteArrayArrayArray12[height][posX + 1][posY] = (byte) 50;
					}
					if (objectDef.aBoolean1677)
						anIntArrayArrayArray438[height][posX][posY] |= 1170;
				}
				if (objectDef.isSolid && class59 != null)
					class59.addClipping(type, posX, posY, objectDef.isWalkable,
							rotation);
				if (objectDef.anInt1673 != 16)
					class64.method990(height, posX, posY, objectDef.anInt1673);
			} else if (type == 1) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId != -1
						|| (objectDef.childrenIDs) != null)
					class3_sub3_sub3 = new AnimatedObject(objectId, 1,
							rotation, i_26_, i_27_, i_28_, i_29_,
							objectDef.animationId, true);
				else
					class3_sub3_sub3 = objectDef.method336(i_26_, i_29_,
							rotation, 1, i_27_, i_28_);
				class64.addWallObject(height, posX, posY, tileHeight,
						class3_sub3_sub3, null, anIntArray616[rotation], 0,
						hash, uid);
				if (objectDef.aBoolean1666) {
					if (rotation == 0)
						aByteArrayArrayArray12[height][posX][(posY + 1)] = (byte) 50;
					else if (rotation == 1)
						aByteArrayArrayArray12[height][posX + 1][posY + 1] = (byte) 50;
					else if (rotation == 2)
						aByteArrayArrayArray12[height][posX + 1][posY] = (byte) 50;
					else if (rotation == 3)
						aByteArrayArrayArray12[height][posX][posY] = (byte) 50;
				}
				if (objectDef.isSolid && class59 != null)
					class59.addClipping(type, posX, posY, objectDef.isWalkable,
							rotation);
			} else if (type == 2) {
				int i_39_ = rotation + 1 & 0x3;
				SceneModel class3_sub3_sub3;
				SceneModel class3_sub3_sub3_40_;
				if (objectDef.animationId == -1
						&& (objectDef.childrenIDs) == null) {
					class3_sub3_sub3 = objectDef.method336(i_26_, i_29_,
							rotation + 4, 2, i_27_, i_28_);
					class3_sub3_sub3_40_ = objectDef.method336(i_26_, i_29_,
							i_39_, 2, i_27_, i_28_);
				} else {
					class3_sub3_sub3 = new AnimatedObject(objectId, 2,
							rotation + 4, i_26_, i_27_, i_28_, i_29_,
							(objectDef.animationId), true);
					class3_sub3_sub3_40_ = new AnimatedObject(objectId, 2,
							i_39_, i_26_, i_27_, i_28_, i_29_,
							(objectDef.animationId), true);
				}
				class64.addWallObject(height, posX, posY, tileHeight,
						class3_sub3_sub3, class3_sub3_sub3_40_,
						anIntArray992[rotation], anIntArray992[i_39_], hash,
						uid);
				if (objectDef.aBoolean1677) {
					if (rotation == 0) {
						anIntArrayArrayArray438[height][posX][posY] |= 585;
						anIntArrayArrayArray438[height][posX][(posY + 1)] |= 1170;
					} else if (rotation == 1) {
						anIntArrayArrayArray438[height][posX][(posY + 1)] |= 1170;
						anIntArrayArrayArray438[height][posX + 1][posY] |= 585;
					} else if (rotation == 2) {
						anIntArrayArrayArray438[height][posX + 1][posY] |= 585;
						anIntArrayArrayArray438[height][posX][posY] |= 1170;
					} else if (rotation == 3) {
						anIntArrayArrayArray438[height][posX][posY] |= 1170;
						anIntArrayArrayArray438[height][posX][posY] |= 585;
					}
				}
				if (objectDef.isSolid && class59 != null)
					class59.addClipping(type, posX, posY, objectDef.isWalkable,
							rotation);
				if (objectDef.anInt1673 != 16)
					class64.method990(height, posX, posY, objectDef.anInt1673);
			} else if (type == 3) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId != -1
						|| (objectDef.childrenIDs) != null)
					class3_sub3_sub3 = new AnimatedObject(objectId, 3,
							rotation, i_26_, i_27_, i_28_, i_29_,
							(objectDef.animationId), true);
				else
					class3_sub3_sub3 = objectDef.method336(i_26_, i_29_,
							rotation, 3, i_27_, i_28_);
				class64.addWallObject(height, posX, posY, tileHeight,
						class3_sub3_sub3, null, anIntArray616[rotation], 0,
						hash, uid);
				if (objectDef.aBoolean1666) {
					if (rotation == 0)
						aByteArrayArrayArray12[height][posX][(posY + 1)] = (byte) 50;
					else if (rotation == 1)
						aByteArrayArrayArray12[height][posX + 1][posY + 1] = (byte) 50;
					else if (rotation == 2)
						aByteArrayArrayArray12[height][posX + 1][posY] = (byte) 50;
					else if (rotation == 3)
						aByteArrayArrayArray12[height][posX][posY] = (byte) 50;
				}
				if (objectDef.isSolid && class59 != null)
					class59.addClipping(type, posX, posY, objectDef.isWalkable,
							rotation);
			} else if (type == 9) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId != -1
						|| (objectDef.childrenIDs) != null)
					class3_sub3_sub3 = new AnimatedObject(objectId, type,
							rotation, i_26_, i_27_, i_28_, i_29_,
							(objectDef.animationId), true);
				else
					class3_sub3_sub3 = objectDef.method336(i_26_, i_29_,
							rotation, type, i_27_, i_28_);
				class64.addInteractiveObject(height, posX, posY, tileHeight, 1,
						1, class3_sub3_sub3, 0, hash, uid);
				if (objectDef.isSolid && class59 != null)
					class59.addClipping(posX, posY, objectDef.sizeX,
							objectDef.sizeY, rotation, objectDef.isWalkable);
			} else {
				if (objectDef.aBoolean1654) {
					if (rotation == 1) {
						int i_41_ = i_29_;
						i_29_ = i_28_;
						i_28_ = i_27_;
						i_27_ = i_26_;
						i_26_ = i_41_;
					} else if (rotation == 2) {
						int i_42_ = i_29_;
						i_29_ = i_27_;
						i_27_ = i_42_;
						i_42_ = i_28_;
						i_28_ = i_26_;
						i_26_ = i_42_;
					} else if (rotation == 3) {
						int i_43_ = i_29_;
						i_29_ = i_26_;
						i_26_ = i_27_;
						i_27_ = i_28_;
						i_28_ = i_43_;
					}
				}
				if (type == 4) {
					SceneModel class3_sub3_sub3;
					if ((objectDef.animationId == -1)
							&& (objectDef.childrenIDs) == null)
						class3_sub3_sub3 = objectDef.method336(i_26_, i_29_, 0,
								4, i_27_, i_28_);
					else
						class3_sub3_sub3 = (new AnimatedObject(objectId, 4, 0,
								i_26_, i_27_, i_28_, i_29_,
								(objectDef.animationId), true));
					class64.addWallDecoration(height, posX, posY, tileHeight,
							class3_sub3_sub3, anIntArray992[rotation],
							rotation * 512, 0, 0, hash, uid);
				} else if (type == 5) {
					int i_44_ = 16;
					int i_45_ = class64.getWallObjectHash(posX, posY, height);
					if (i_45_ > 0)
						i_44_ = ObjectDefinition.forID(i_45_ >> 14 & 0x7fff).anInt1673;
					SceneModel class3_sub3_sub3;
					if ((objectDef.animationId == -1)
							&& (objectDef.childrenIDs) == null)
						class3_sub3_sub3 = objectDef.method336(i_26_, i_29_, 0,
								4, i_27_, i_28_);
					else
						class3_sub3_sub3 = (new AnimatedObject(objectId, 4, 0,
								i_26_, i_27_, i_28_, i_29_,
								(objectDef.animationId), true));
					class64.addWallDecoration(height, posX, posY, tileHeight,
							class3_sub3_sub3, anIntArray992[rotation],
							rotation * 512, i_44_
									* Client.anIntArray897[rotation],
							Client.anIntArray162[rotation] * i_44_, hash, uid);
				} else if (type == 6) {
					SceneModel class3_sub3_sub3;
					if ((objectDef.animationId == -1)
							&& (objectDef.childrenIDs) == null)
						class3_sub3_sub3 = objectDef.method336(i_26_, i_29_, 0,
								4, i_27_, i_28_);
					else
						class3_sub3_sub3 = (new AnimatedObject(objectId, 4, 0,
								i_26_, i_27_, i_28_, i_29_,
								(objectDef.animationId), true));
					class64.addWallDecoration(height, posX, posY, tileHeight,
							class3_sub3_sub3, 256, rotation, 0, 0, hash, uid);
				} else if (type == 7) {
					SceneModel class3_sub3_sub3;
					if ((objectDef.animationId != -1)
							|| (objectDef.childrenIDs) != null)
						class3_sub3_sub3 = (new AnimatedObject(objectId, 4, 0,
								i_26_, i_27_, i_28_, i_29_,
								(objectDef.animationId), true));
					else
						class3_sub3_sub3 = objectDef.method336(i_26_, i_29_, 0,
								4, i_27_, i_28_);
					class64.addWallDecoration(height, posX, posY, tileHeight,
							class3_sub3_sub3, 512, rotation, 0, 0, hash, uid);
				} else if (type == 8) {
					SceneModel class3_sub3_sub3;
					if ((objectDef.animationId == -1)
							&& (objectDef.childrenIDs) == null)
						class3_sub3_sub3 = objectDef.method336(i_26_, i_29_, 0,
								4, i_27_, i_28_);
					else
						class3_sub3_sub3 = (new AnimatedObject(objectId, 4, 0,
								i_26_, i_27_, i_28_, i_29_,
								(objectDef.animationId), true));
					class64.addWallDecoration(height, posX, posY, tileHeight,
							class3_sub3_sub3, 768, rotation, 0, 0, hash, uid);
				}
			}
		}
	}

	static final void resetGameframe() {
		if (gameframeLoaded) {
			Client.redStone1_2 = null;
			Client.backhMid1 = null;
			redStone2 = null;
			redStone3 = null;
			Client.chatButtonBuffer = null;
			backRight2Buffer = null;
			gameScreenBuffer = null;
			sideIcons = null;
			upperSideIconsBuffer = null;
			mapBack = null;
			redStone2_4 = null;
			tabAreaBuffer = null;
			backLeft2Buffer = null;
			backhmid2Buffer = null;
			backvmid1Buffer = null;
			backvmid3Buffer = null;
			backBase2 = null;
			redStone1 = null;
			Client.redStone3_2 = null;
			backBase1 = null;
			tabAreaOffsets = null;
			redStone2_2 = null;
			backLeft1Buffer = null;
			redStone1_4 = null;
			gameframeLoaded = false;
			chatboxAreaBuffer = null;
			compassDestination = null;
			Client.chatBack = null;
			Client.landscapeDestination = null;
			redStone1_3 = null;
			minimapBuffer = null;
			chatAreaOffsets = null;
			invBack = null;
			backRight1Buffer = null;
			backTop1Buffer = null;
			redStone2_3 = null;
			Client.gamescreenAreaOffsets = null;
			compassSource = null;
			backvmid2Buffer = null;
			loverSideIconsBuffer = null;
			landscapeSource = null;
		}
	}

	static final RSFont constructFont() {
		RSFont instance = new RSFont(spriteOffsetY, spriteWidth, spriteHeight,
				spritePallete, spritePixels);
		resetSprites();
		return instance;
	}

	public static final void lowMem() {
		SceneGraph.lowMem = true;
		lowMem = true;
	}

	static final AnimationLoader initAnimationLoader(
			FileSystem skeletonFetcher, FileSystem skinFetcher, boolean swap,
			int id) {
		boolean pass = true;
		int[] entries = skinFetcher.getChildEntries(id);
		for (int entry = 0; entries.length > entry; entry++) {
			byte[] entryBuffer = skinFetcher.getBuffer(entries[entry], id);
			if (entryBuffer == null)
				pass = false;
			else {
				int index = entryBuffer[1] & 0xff
						| (entryBuffer[0] & 0xff) << 8;
				byte[] fileBuffer;
				if (!swap)
					fileBuffer = skeletonFetcher.getBuffer(0, index);
				else
					fileBuffer = skeletonFetcher.getBuffer(index, 0);
				if (fileBuffer == null)
					pass = false;
			}
		}
		if (!pass)
			return null;
		AnimationLoader loader;
		try {
			loader = new AnimationLoader(skinFetcher, skeletonFetcher, id, swap);
		} catch (Exception exception) {
			return null;
		}
		return loader;
	}

	static final void processLogout() {
		if (worldConnection != null)
			worldConnection.close();
		worldConnection = null;
		Client.clearCache();
		Client.sceneGraph.dispose();
		for (int i_0_ = 0; i_0_ < 5; i_0_++)
			Client.collisionMaps[i_0_].resetFlags();
		System.gc();
		tempMusicDelay = 0;
		currentMusic = -1;
		Client.setGameState(10);
	}

	static final void moveEntity(Entity entity) {
		entity.currentAnimation = entity.idleAnimation;
		do {
			if (entity.walkQueueLocationIndex == 0)
				entity.anInt1866 = 0;
			else {
				if (entity.currentAnimationId != -1
						&& entity.animationDelay == 0) {
					Sequence sequence = Sequence
							.forID(entity.currentAnimationId);
					if (entity.anInt1906 > 0 && sequence.speedupType == 0) {
						entity.anInt1866++;
						break;
					}
					if (entity.anInt1906 <= 0 && sequence.walkProperties == 0) {
						entity.anInt1866++;
						break;
					}
				}
				int curX = entity.x;
				int curY = entity.y;
				int queuedX = (entity.walkQueueX[entity.walkQueueLocationIndex - 1] * 128 + entity.size * 64);
				int queuedY = (entity.size * 64 + (entity.walkQueueY[entity.walkQueueLocationIndex - 1]) * 128);
				if (queuedX - curX > 256 || queuedX - curX < -256
						|| queuedY - curY > 256 || queuedY - curY < -256) {
					entity.x = queuedX;
					entity.y = queuedY;
				} else {
					if (curX < queuedX) {
						if (curY < queuedY)
							entity.turnDirection = 1280;
						else if (curY <= queuedY)
							entity.turnDirection = 1536;
						else
							entity.turnDirection = 1792;
					} else if (queuedX >= curX) {
						if (curY < queuedY)
							entity.turnDirection = 1024;
						else
							entity.turnDirection = 0;
					} else if (curY < queuedY)
						entity.turnDirection = 768;
					else if (curY > queuedY)
						entity.turnDirection = 256;
					else
						entity.turnDirection = 512;
					int direction = ((-(entity.directionDegrees) + (entity.turnDirection)) & 0x7ff);
					int steps = 4;
					if (((entity.directionDegrees) != (entity.turnDirection))
							&& (entity.interactingEntityIndex) == -1
							&& (entity.turnValue) != 0)
						steps = 2;
					if ((entity.walkQueueLocationIndex) > 2)
						steps = 6;
					if ((entity.walkQueueLocationIndex) > 3)
						steps = 8;
					int anim = (entity.turn180Animation);
					if (direction > 1024)
						direction -= 2048;
					if (direction < -256 || direction > 256) {
						if (direction >= 256 && direction < 768)
							anim = entity.turn90CCAnimation;
						else if (direction >= -768 && direction <= -256)
							anim = entity.turn90CWAnimation;
					} else
						anim = (entity.walkAnimation);
					if ((entity.anInt1866) > 0
							&& (entity.walkQueueLocationIndex) > 1) {
						entity.anInt1866--;
						steps = 8;
					}
					if (anim == -1)
						anim = (entity.walkAnimation);
					entity.currentAnimation = anim;
					if (entity.runningFlags[(entity.walkQueueLocationIndex) - 1])
						steps <<= 1;
					if (curX >= queuedX) {
						if (curX > queuedX) {
							entity.x -= steps;
							if (queuedX > entity.x)
								entity.x = queuedX;
						}
					} else {
						entity.x += steps;
						if (queuedX < (entity.x))
							entity.x = queuedX;
					}
					if (queuedY <= curY) {
						if (curY > queuedY) {
							entity.y -= steps;
							if (entity.y < queuedY)
								entity.y = queuedY;
						}
					} else {
						entity.y += steps;
						if (queuedY < (entity.y))
							entity.y = queuedY;
					}
					if ((entity.x) == queuedX && queuedY == entity.y) {
						entity.walkQueueLocationIndex--;
						if ((entity.anInt1906) > 0)
							entity.anInt1906--;
					}
					if (steps >= 8
							&& ((entity.currentAnimation) == (entity.walkAnimation))
							&& (entity.runAnimation) != -1)
						entity.currentAnimation = (entity.runAnimation);
				}
			}
		} while (false);
	}

	static final Sprite[] fetchMultiplerSprite(int i, FileSystem class18,
			int i_41_) {
		if (!spriteExists(class18, i, i_41_))
			return null;
		return Client.constructSprites();
	}

	static final Sprite fetchSingleSprite(int i, FileSystem class18, int i_4_) {
		if (!spriteExists(class18, i_4_, i))
			return null;
		return constructSprite();
	}

	static final int getRotatedLandscapeChunkY(int rotation, int y, int i_26_,
			int x, int objectSizeY, int objectSizeX) {
		rotation &= 0x3;
		if ((i_26_ & 0x1) == 1) {
			int xSize = objectSizeX;
			objectSizeX = objectSizeY;
			objectSizeY = xSize;
		}
		if (rotation == 0)
			return y;
		if (rotation == 1)
			return 7 - x - (objectSizeX - 1);
		if (rotation == 2)
			return 7 - y - (objectSizeY - 1);
		return x;
	}

	static final RSString getFormattedValue(int value) {
		RSString class63 = RSString.valueOf(value);
		for (int i_19_ = class63.length() - 3; i_19_ > 0; i_19_ -= 3)
			class63 = linkRSStrings(new RSString[] {
					class63.substring(0, i_19_), aClass63_324,
					class63.substring(i_19_) });
		if (class63.length() > 8)
			class63 = linkRSStrings(new RSString[] { gre,
					class63.substring(0, class63.length() - 8), million2,
					Client.aClass63_736, class63, yString });
		else if (class63.length() > 4)
			class63 = linkRSStrings(new RSString[] { cya,
					class63.substring(0, class63.length() - 4), thousand2,
					Client.aClass63_736, class63, yString });
		return linkRSStrings(new RSString[] { space, class63 });
	}

	static final boolean isCharacter(int i) {
		if (i < 32)
			return false;
		if (i == 127)
			return false;
		if (i >= 129 && i <= 159)
			return false;
		return true;
	}

	static final void drawGameScreenBuffer() {
		try {
			gameScreenBuffer.drawGraphics(canvas.getGraphics(), 0, 0);
		} catch (Exception exception) {
			canvas.repaint();
		}
	}

	static final void setupIkitFetcher(FileSystem class18, FileSystem class18_7_) {
		Client.iKitFetcher = class18;
		iKitModelFetcher = class18_7_;
		iKitLen = Client.iKitFetcher.getChildCount(3);
	}

	static final void processMouse() {
		if (Client.activeInterfaceType == 0) {
			menuActionName[0] = cancel;
			menuActionOpcode[0] = 1002;
			Client.menuActionCount = 1;
			activeComponentId = -1;
			boolean bool = false;
			activeYellowboxId = -1;
			if (lastMouseX > 0 && lastMouseY > 0 && lastMouseX < clientFrame.getWidth() && lastMouseY < clientFrame.getHeight()) {
				build3dScreenMenu();
			}
			activeGameScreen = activeComponentId;
			activeComponentId = -1;
			Client.gamescreenYellowBox = activeYellowboxId;
			activeYellowboxId = -1;
			if (activeTabArea != activeComponentId) {
				Client.redrawTabArea = true;
				activeTabArea = activeComponentId;
			}
			activeComponentId = -1;
			if (activeYellowboxId != tabareaYellowbox) {
				Client.redrawTabArea = true;
				tabareaYellowbox = activeYellowboxId;
			}
			activeYellowboxId = -1;
			if ((Client.unwalkableChatboxId != -1 || walkableChatboxId != -1)
					&& Client.activeChatbox != activeComponentId) {
				Client.activeChatbox = activeComponentId;
				redrawChatbox = true;
			}
			if ((Client.unwalkableChatboxId != -1 || walkableChatboxId != -1)
					&& (chatboxYellowbox != activeYellowboxId)) {
				chatboxYellowbox = activeYellowboxId;
				redrawChatbox = true;
			}
			while (!bool) {
				bool = true;
				for (int i_7_ = 0; i_7_ < Client.menuActionCount - 1; i_7_++) {
					if (menuActionOpcode[i_7_] < 1000
							&& menuActionOpcode[i_7_ + 1] > 1000) {
						bool = false;
						RSString class63 = menuActionName[i_7_];
						menuActionName[i_7_] = menuActionName[i_7_ + 1];
						menuActionName[i_7_ + 1] = class63;
						int i_8_ = menuActionOpcode[i_7_];
						menuActionOpcode[i_7_] = menuActionOpcode[i_7_ + 1];
						menuActionOpcode[i_7_ + 1] = i_8_;
						i_8_ = menuActionCmd3[i_7_];
						menuActionCmd3[i_7_] = (menuActionCmd3[i_7_ + 1]);
						menuActionCmd3[i_7_ + 1] = i_8_;
						i_8_ = menuActionCmd2[i_7_];
						menuActionCmd2[i_7_] = menuActionCmd2[i_7_ + 1];
						menuActionCmd2[i_7_ + 1] = i_8_;
						i_8_ = Client.menuActionIndex[i_7_];
						Client.menuActionIndex[i_7_] = (Client.menuActionIndex[i_7_ + 1]);
						Client.menuActionIndex[i_7_ + 1] = i_8_;
					}
				}
			}
		}
	}
	
	static final void drawTooltip() {
		/*if (Client.menuActionCount >= 2) {
			RSString tooltip = menuActionName[Client.menuActionCount - 1];
			
			if (Client.menuActionCount > 2)
				tooltip = linkRSStrings(
						new RSString[] {
								tooltip, whi2,
								RSString.valueOf(Client.menuActionCount - 2),
								moreOptions
						});
			Client.boldFont.drawString(tooltip, 4, 15, 16777215, true);//TODO wtf crashing me 
		}*/
	}

	static final void method367(int x2, int xOffset, int y2, int yOffset) {
		x2 = 0;
		y2 = 0;
		for (int y = y2; y2 + yOffset >= y; y++) {
			for (int x = x2; x2 + xOffset >= x; x++) {
				if (x >= 0 && x < 104 && y >= 0 && y < 104) {
					aByteArrayArrayArray12[0][x][y] = (byte) 127;
					if (x == x2 && x > 0)
						tileHeight[0][x][y] = (tileHeight[0][x - 1][y]);
					if (x == xOffset + x2 && x < 103)
						tileHeight[0][x][y] = (tileHeight[0][x + 1][y]);
					if (y2 == y && y > 0)
						tileHeight[0][x][y] = (tileHeight[0][x][y - 1]);
					if (y == yOffset + y2 && y < 103)
						tileHeight[0][x][y] = (tileHeight[0][x][y + 1]);
				}
			}
		}
	}

	static final void method366(int i, int i_0_) {
		long l = (long) (i + (i_0_ << 16));
		BufferedRequest class3_sub3_sub13 = (BufferedRequest) aClass40_14
				.get(l);
		if (class3_sub3_sub13 != null)
			aClass30_2033.method671(class3_sub3_sub13);
	}

	static final IndexedSprite constructIndexedSprite() {
		IndexedSprite sprite = new IndexedSprite();
		sprite.trimWidth = spriteTrimWidth;
		sprite.trimHeight = spriteTrimHeight;
		sprite.offsetX = spriteOffsetX[0];
		sprite.offsetY = spriteOffsetY[0];
		sprite.width = spriteWidth[0];
		sprite.height = spriteHeight[0];
		sprite.pallete = spritePallete;
		sprite.pixels = spritePixels[0];
		resetSprites();
		return sprite;
	}

	static final void build3dScreenMenu() {
		//performAction(13, walkHere, lastMouseY, lastMouseX, 0);
		int i = -1;
		for (int len = 0; len < Model.anInt1998; len++) {
			int info = Model.anIntArray2003[len];
			int index = info >> 14 & 0x7fff;
			int y = (info & 0x3fd7) >> 7;
			int type = (info & 0x73fadaae) >> 29;
			int x = info & 0x7f;
			if (i != info) {
				i = info;
				if (type == 2 && Client.sceneGraph.getUID(height, x, y, info) >= 0) {
					ObjectDefinition oDef = ObjectDefinition.forID(index);
					if (oDef == null)
						continue;
					int a = Client.sceneGraph.getUID(height, x, y, info);
					int locType = a & 0x1f;
					int locDirection = a >> 6 & 0x3;
					performAction(1001, RSString.createRSString("Name=@cya@"+oDef.name+"@whi@, id=@cya@"+oDef.id + "@whi@, sizeX=@cya@"+oDef.sizeX + "@whi@, sizeZ=@cya@"+oDef.sizeY + "@whi@, walkable=@cya@"+oDef.isWalkable + "@whi@, type=@cya@"+locType + "@whi@, direction=@cya@"+locDirection), y, x, oDef.id << 14);
				}
			}
		}
	}

	static final void startup() {
		if (loadingStage == 0) {
			Runtime runtime = Runtime.getRuntime();
			int mem = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
			long time = System.currentTimeMillis();
			if (allocatingTime == 0L)
				allocatingTime = time;
			if (mem > 16384 && time - allocatingTime < 5000L) {
				if (time - allocateGcTime > 1000L) {
					System.gc();
					allocateGcTime = time;
				}
				Client.loadingText = allocatingMemory;
				loadingBarPercent = 5;
			} else {
				Client.loadingText = allocatedMemory;
				loadingStage = 10;
				loadingBarPercent = 5;
			}
		} else if (loadingStage == 10) {
			sceneGraph = new SceneGraph(4, 104, 104, tileHeight);
			for (int i = 0; i < 5; i++)
				Client.collisionMaps[i] = new CollisionMap(104, 104);
			landScapeAsSprite = new Sprite(512, 512);

			gameScreenBuffer = constructGraphicsBuffer(canvas, sizeX, sizeY);
			objectScreenBuffer = constructGraphicsBuffer(GUI.objectRenderCanvas, GUI.objectRenderCanvas.getWidth(), GUI.objectRenderCanvas.getHeight());
			Graphics2D.resetpixels();

			Client.loadingText = startingGameEngine;
			loadingStage = 20;
			loadingBarPercent = 10;
		} else if (loadingStage == 20) {
			int[] is = new int[9];
			for (int i = 0; i < 9; i++) {
				int i_18_ = i * 32 + 128 + 15;
				int i_19_ = Rasterizer.sineTable[i_18_];
				int i_20_ = i_18_ * 3 + 600;
				is[i] = i_20_ * i_19_ >> 16;
			}
			SceneGraph.method958(is, 500, 800, 512, 334);
			loadingStage = 30;
			Client.loadingText = preparedVisibilityMap;
			loadingBarPercent = 12;
		} else if (loadingStage == 30) {
			cacheIndex0 = initCacheWorker(false, true, 0, true);
			cacheIndex1 = initCacheWorker(false, true, 1, true);
			cacheIndex2 = initCacheWorker(true, true, 2, false);
			cacheIndex3 = initCacheWorker(false, true, 3, true);
			cacheIndex4 = initCacheWorker(false, true, 4, true);
			cacheIndex5 = initCacheWorker(true, true, 5, true);
			cacheIndex6 = initCacheWorker(true, false, 6, true);
			cacheIndex7 = initCacheWorker(false, true, 7, true);
			spriteWorker = initCacheWorker(false, true, 8, true);
			textureWorker = initCacheWorker(false, true, 9, true);
			cacheIndex10 = initCacheWorker(false, true, 10, true);
			cacheIndex11 = initCacheWorker(false, true, 11, true);
			Client.loadingText = connectingUpdateServer;
			loadingStage = 40;
			loadingBarPercent = 20;
		} else if (loadingStage == 40) {
			int percent = 0;
			percent += cacheIndex0.method600() * 5 / 100;
			percent += cacheIndex1.method600() * 5 / 100;
			percent += cacheIndex2.method600() * 5 / 100;
			percent += cacheIndex3.method600() * 5 / 100;
			percent += cacheIndex4.method600() * 5 / 100;
			percent += cacheIndex5.method600() * 5 / 100;
			percent += cacheIndex6.method600() * 5 / 100;
			percent += cacheIndex7.method600() * 45 / 100;
			percent += spriteWorker.method600() * 5 / 100;
			percent += textureWorker.method600() * 5 / 100;
			percent += cacheIndex10.method600() * 5 / 100;
			percent += cacheIndex11.method600() * 5 / 100;
			if (percent < 70) {
				if (percent != 0)
					Client.loadingText = loadedUpdateList;
				loadingBarPercent = 30;
			} else {
				loadingBarPercent = 30;
				Client.loadingText = loadedUpdateList;
				loadingStage = 45;
	 	   }
			//loadingBarPercent = 30;
			//Client.loadingText = loadedUpdateList;
			//loadingStage = 45;
		} else if (loadingStage == 45) {
			loadingStage = 50;
			loadingBarPercent = 35;
			Client.loadingText = preparedSoundEngine;
		} else if (loadingStage == 50) {
			/*smallFont = getFont(Client.nothing, p11full, spriteWorker);
			regularFont = getFont(Client.nothing, p12full, spriteWorker);
			boldFont = getFont(Client.nothing, b12full, spriteWorker);
			fancyFont = getFont(Client.nothing, q8full, spriteWorker);
			Client.loadingText = loadedFonts;
			fonts = new RSFont[] { smallFont, regularFont, boldFont, fancyFont };
			loadingStage = 60;
			loadingBarPercent = 40;*/
			int times = 0;
			if (smallFont == null)
				smallFont = getFont(nothing, p11full, spriteWorker);
			else
				times++;
			if (regularFont == null)
				regularFont = getFont(nothing, p12full, spriteWorker);
			else
				times++;
			if (boldFont == null)
				boldFont = getFont(nothing, b12full, spriteWorker);
			else
				times++;
			if (fancyFont == null)
				fancyFont = getFont(nothing, q8full, spriteWorker);
			else
				times++;
			if (times < 4) {
				Client.loadingText = loadedFonts;
				loadingBarPercent = 40;
			} else {
				Client.loadingText = loadedFonts;
				fonts = new RSFont[] {
						smallFont,
						regularFont,
						boldFont,
						fancyFont
				};
				loadingStage = 60;
				loadingBarPercent = 40;
			}
		} else if (loadingStage == 60) {
			loadingBarPercent = 50;
			Client.loadingText = loadedTitleScreen;
			loadingStage = 70;
		} else if (loadingStage == 70) {
			if (!cacheIndex2.method570()) {
				Client.loadingText = loadedConfigs;
				loadingBarPercent = 60;
			} else {
				setupUnderlayFetcher(cacheIndex2);// TODO
				setupOverlayFetcher(cacheIndex2);
				setupIkitFetcher(cacheIndex2, cacheIndex7);
				setupObjectFetcher(cacheIndex7, lowMem, cacheIndex2);
				setupItemFetcher(cacheIndex7, cacheIndex2);
				setupAnimationFetcher(cacheIndex1, cacheIndex0, cacheIndex2);
				setupSpotAnimtionFetcher(cacheIndex7, cacheIndex2);
				setupVarbitFetcher(cacheIndex2);
				setupVarpFetcher(cacheIndex2);
				loadingBarPercent = 60;
				loadingStage = 80;
				Client.loadingText = loadedConfigs;
			}
		} else if (loadingStage == 80) {
			int count = 0;//TODO
			if (compass == null)
				compass = getSingleSprite(spriteWorker, nothing, compassString);
			else
				count++;
			if (mapEdge == null)
				mapEdge = getSingleSprite(spriteWorker, nothing, mapedgeString);
			else
				count++;
			if (mapScene == null)
				mapScene = getMultipleIndexedSprites(spriteWorker, mapsceneString, nothing);
			else
				count++;
			if (mapFunction == null)
				mapFunction = Client.getMultipleSprite(spriteWorker, nothing, mapfunctionString);
			else
				count++;
			if (hitMarks == null)
				hitMarks = Client.getMultipleSprite(spriteWorker, nothing, hitmarksString);
			else
				count++;
			if (skullIcon == null)
				skullIcon = Client.getMultipleSprite(spriteWorker, nothing, headiconspkString);
			else
				count++;
			count++;
			if (hintIcon == null)
				hintIcon = Client.getMultipleSprite(spriteWorker, nothing, headiconshintString);
			else
				count++;
			if (multiIconSprite == null)
				multiIconSprite = getSingleSprite(spriteWorker, nothing, overlaymultiwayString);
			else
				count++;
			if (mapMarker == null)
				mapMarker = Client.getMultipleSprite(spriteWorker, nothing, mapmarkerString);
			else
				count++;
			if (crosses == null)
				crosses = Client.getMultipleSprite(spriteWorker, nothing, crossString);
			else
				count++;
			if (mapDot == null)
				mapDot = Client.getMultipleSprite(spriteWorker, nothing, mapdotsString);
			else
				count++;
			if (scrollBar == null)
				scrollBar = getMultipleIndexedSprites(spriteWorker, scrollbarString, nothing);
			else
				count++;
			if (mapBack == null)
				mapBack = method63(spriteWorker, mapback, nothing);
			else
				count++;
			if (count < 14) {
				Client.loadingText = loadedSprites;
				loadingBarPercent = 70;
			} else {
				mapEdge.resize();
				/*
				 * int i_22_ = (int) (Math.random() * 21.0) - 10; int i_23_ =
				 * (int) (Math.random() * 21.0) - 10; int i_24_ = (int)
				 * (Math.random() * 41.0) - 20; int i_25_ = (int) (Math.random()
				 * * 21.0) - 10; for (int i_26_ = 0; i_26_ < mapFunction.length;
				 * i_26_++) mapFunction[i_26_].adjustColors(i_22_ + i_24_, i_23_
				 * + i_24_, i_25_ + i_24_); mapScene[0].adjustColors(i_22_ +
				 * i_24_, i_23_ + i_24_, i_25_ + i_24_);
				 */
				loadingBarPercent = 70;
				loadingStage = 85;
				Client.loadingText = loadedSprites;
			}/*
				mapEdge = getSingleSprite(spriteWorker, Client.nothing,
						mapedgeString);
				mapScene = getMultipleIndexedSprites(spriteWorker,
						mapsceneString, Client.nothing);
				mapFunction = Client.getMultipleSprite(spriteWorker,
						Client.nothing, Client.mapfunctionString);
				hitMarks = Client.getMultipleSprite(spriteWorker,
						Client.nothing, hitmarksString);
				skullIcon = Client.getMultipleSprite(spriteWorker,
						Client.nothing, headiconspkString);
				prayerIcon = Client.getMultipleSprite(spriteWorker,
						Client.nothing, headiconsprayerSprite);
				hintIcon = Client.getMultipleSprite(spriteWorker,
						Client.nothing, headiconshintString);
				multiIconSprite = getSingleSprite(spriteWorker, Client.nothing,
						overlaymultiwayString);
				mapMarker = Client.getMultipleSprite(spriteWorker,
						Client.nothing, mapmarkerString);
				crosses = Client.getMultipleSprite(spriteWorker, Client.nothing,
						crossString);
				mapDot = Client.getMultipleSprite(spriteWorker, Client.nothing,
						mapdotsString);
				scrollBar = getMultipleIndexedSprites(spriteWorker,
						scrollbarString, Client.nothing);
				modIcons = getMultipleIndexedSprites(spriteWorker,
						modIconsString, Client.nothing);
			mapBack = method63(spriteWorker, mapback, nothing);
			// mapEdge.resize();
			loadingBarPercent = 70;
			loadingStage = 85;*/
			Client.loadingText = loadedSprites;
		} else if (loadingStage == 85) {
			loadingStage = 90;
			Client.loadingText = loadedGameScreen;
			loadingBarPercent = 80;
		} else if (loadingStage == 90) {
			if (!textureWorker.method570()) {
				Client.loadingText = loadedSprites;
				loadingBarPercent = 90;
			} else {
				TextureLoader texture = new TextureLoader(textureWorker, spriteWorker, 30, 0.8, lowMem ? 64 : 128);
				Rasterizer.setTexture(texture);
				Rasterizer.generatePalette(0.8);
				Client.loadingText = loadedTextures;
				loadingStage = 110;
				loadingBarPercent = 90;
			}
		} else if (loadingStage == 110) {
			Client.loadingText = loadedInputHandler;
			loadingStage = 120;
			loadingBarPercent = 94;
		} else if (loadingStage == 120) {
			//Huffman huffman = new Huffman(cacheIndex10.getFileByName(
			//		huffmanString, Client.nothing));
			//initHuffman(huffman);
			Client.loadingText = loadedWorldPack;
			loadingStage = 130;
			loadingBarPercent = 96;
		} else if (loadingStage == 130) {
			Client.loadingText = Client.loadedInterfaces;
			loadingBarPercent = 100;
			loadingStage = 140;
		} else if (loadingStage == 140) {
			loadGameframe(GUI.minimapPanel);
			refreshMap();
			Client.setGameState(10);
		}
	}

	static final int packText(RSString string, RSByteBuffer stream, int offset) {
		int pos = stream.index;
		stream.putSmart(string.length);
		RSByteBuffer class3_sub12_1_ = stream;
		int i_2_ = class3_sub12_1_.index;
		int i_3_ = huffman.textPack(stream.buf, offset, string.buffer,
				stream.index, string.length);
		class3_sub12_1_.index = i_3_ + i_2_;
		return stream.index - pos;
	}

	final void processInput() {
		while (handleKeyboardInput()) {
			if (Client.unwalkableInterfaceId != -1
					&& reportInterfaceID == Client.unwalkableInterfaceId) {
				if (keyCode == 85 && Client.reportAbuseInput.length() > 0)
					Client.reportAbuseInput = Client.reportAbuseInput
							.substring(0, Client.reportAbuseInput.length() - 1);
				if ((Client.isValidKeyChar(keyChar) || keyChar == 32)
						&& Client.reportAbuseInput.length() < 12)
					Client.reportAbuseInput = Client.reportAbuseInput
							.add(keyChar);
			} else if (messagePromptRaised) {
				if (keyCode == 85 && Client.privateChatPromptInput.length() > 0) {
					Client.privateChatPromptInput = Client.privateChatPromptInput
							.substring(0,
									Client.privateChatPromptInput.length() - 1);
					redrawChatbox = true;
				}
				if (Client.isCharacter(keyChar)
						&& Client.privateChatPromptInput.length() < 80) {
					Client.privateChatPromptInput = Client.privateChatPromptInput
							.add(keyChar);
					redrawChatbox = true;
				}
				if (keyCode == 84) {
					messagePromptRaised = false;
					redrawChatbox = true;
				}
			} else if (inputDialogState == 1) {
				if (keyCode == 85 && Client.textInput.length() > 0) {
					Client.textInput = Client.textInput.substring(0,
							Client.textInput.length() - 1);
					redrawChatbox = true;
				}
				if (isNumber(keyChar) && Client.textInput.length() < 10) {
					Client.textInput = Client.textInput.add(keyChar);
					redrawChatbox = true;
				}
				if (keyCode == 84) {
					if (Client.textInput.length() > 0) {
						int i = 0;
						if (Client.textInput.method946())
							i = Client.textInput.toInt();
					}
					redrawChatbox = true;
					inputDialogState = 0;
				}
			} else if (inputDialogState == 2) {
				if (keyCode == 85 && Client.textInput.length() > 0) {
					Client.textInput = Client.textInput.substring(0,
							Client.textInput.length() - 1);
					redrawChatbox = true;
				}
				if ((Client.isValidKeyChar(keyChar) || keyChar == 32)
						&& Client.textInput.length() < 12) {
					Client.textInput = Client.textInput.add(keyChar);
					redrawChatbox = true;
				}
				if (keyCode == 84) {
					inputDialogState = 0;
					redrawChatbox = true;
				}
			} else if (inputDialogState == 3) {
				if (keyCode == 85 && Client.textInput.length() > 0) {
					Client.textInput = Client.textInput.substring(0,
							Client.textInput.length() - 1);
					redrawChatbox = true;
				}
				if (Client.isCharacter(keyChar)
						&& Client.textInput.length() < 40) {
					Client.textInput = Client.textInput.add(keyChar);
					redrawChatbox = true;
				}
			} else if (Client.unwalkableChatboxId == -1
					&& fullScreenChatboxId == -1) {
				if (keyCode == 85 && Client.inputText.length() > 0) {
					Client.inputText = Client.inputText.substring(0,
							Client.inputText.length() - 1);
					redrawChatbox = true;
				}
				if (Client.isCharacter(keyChar)
						&& Client.inputText.length() < 80) {
					Client.inputText = Client.inputText.add(keyChar);
					redrawChatbox = true;
				}
				if (keyCode == 84 && Client.inputText.length() > 0) {
					// if (privilege == 2) {
					if (Client.inputText.equals(Client.dropClient)) {
						dropClient();
					}
					if (Client.inputText.equals(fpsOn)) {
						NpcDefinition.dumpNpcImages();
						showFps = true;
					}
					if (Client.inputText.equals(Client.fpsOff))
						showFps = false;
					if (Client.inputText.equals(noclip)) {
						for (int z = 0; z < 5; z++) {
							for (int x = 1; x < 103; x++) {
								for (int y = 1; y < 103; y++)
									Client.collisionMaps[z].collisionFlags[x][y] = 0;
							}
						}
					}
					if (Client.inputText.equals(Client.errorTest)
							&& modeWhere == 2)
						throw new RuntimeException();
					if (Client.inputText.equals(Client.hiddenButtonTestString))
						hiddenButtonTest = true;
					// }
					redrawChatbox = true;
					Client.inputText = Client.nothing;
				}
			}
		}
	}

	static final void setupAnimationFetcher(FileSystem class18,
			FileSystem class18_6_, FileSystem class18_7_) {
		sequenceFetcher = class18_7_;
		skeletonFetcher = class18;
		skinsFetcher = class18_6_;
	}

	static final void method665() {
		synchronized (anObject821) {
			if (Client.anInt1465 != 0) {
				Client.anInt1465 = 1;
				try {
					anObject821.wait();
				} catch (InterruptedException interruptedexception) {
					/* empty */
				}
			}
		}
	}

	static final void drawTextOnScreen(RSString line1, RSString line2,
			boolean fillbox) {
		/*
		 * if (redrawGameframe) { redrawGameframe = false; drawMinimapBuffer();
		 * sidePanelRedrawn = true; chatPanelRedrawn = true; iconsRedrawn =
		 * true; } initGameScreen(); int y = 151; y -= 3;
		 * regularFont.drawCenteredString(line1, 257, y, 0);
		 * regularFont.drawCenteredString(line1, 256, y - 1, 16777215); if
		 * (line2 != null) { y += 15; if (fillbox) { int x =
		 * (regularFont.getStringWidth(line2) + 4); Graphics2D.fillRect(257 - x
		 * / 2, y - 11, x, 11, 0); } regularFont.drawCenteredString(line2, 257,
		 * y, 0); regularFont.drawCenteredString(line2, 256, y - 1, 16777215); }
		 * Client.drawGameScreenBuffer();
		 */
	}

	static final void setupVarbitFetcher(FileSystem class18) {
		varbitFetcher = class18;
	}

	static final synchronized void method493(int i) {
		method689(i);
	}

	static final RSString unpackText(RSByteBuffer stream) {
		RSString string;
		try {
			RSString encodedString = new RSString();
			encodedString.length = stream.getUSmart();
			if (encodedString.length > 32767)
				encodedString.length = 32767;
			encodedString.buffer = new byte[encodedString.length];
			stream.index += huffman.textUnpack(stream.buf, encodedString.length,
					encodedString.buffer, stream.index, 0);
			string = encodedString;
		} catch (Exception exception) {
			return cabbage;
		}
		return string;
	}

	/*
	 * static final void sendMapRegion(boolean second) { secondMapRegion =
	 * second; if (secondMapRegion) { int regionY = inputBuffer.getULEShort();
	 * int localX = inputBuffer.getULEShortA(); int height =
	 * inputBuffer.getUByteA(); inputBuffer.startBitAccess(); for (int z = 0; z
	 * < 4; z++) { for (int x = 0; x < 13; x++) { for (int y = 0; y < 13; y++) {
	 * int i_9_ = inputBuffer.getBits(1); if (i_9_ == 1)
	 * anIntArrayArrayArray1601[z][x][y] = inputBuffer .getBits(26); else
	 * anIntArrayArrayArray1601[z][x][y] = -1; } } } inputBuffer.endBitAccess();
	 * int regions = (packetSize - inputBuffer.index) / 16; xtea = new
	 * int[regions][4]; for (int region = 0; regions > region; region++) { for
	 * (int z = 0; z < 4; z++) xtea[region][z] = inputBuffer.getInt1(); } int
	 * regionX = inputBuffer.getUShortA(); int localY =
	 * inputBuffer.getUShortA(); landscapeFileBuffer = new byte[regions][];
	 * currentLandscapeFiles = new int[regions]; currentMapFiles = new
	 * int[regions]; currentRegions = new int[regions]; mapFileBuffer = new
	 * byte[regions][]; regions = 0; for (int z = 0; z < 4; z++) { for (int x =
	 * 0; x < 13; x++) { for (int y = 0; y < 13; y++) { int i_18_ =
	 * (anIntArrayArrayArray1601[z][x][y]); if (i_18_ != -1) { int i_19_ =
	 * (i_18_ & 0x3ff8) >> 3; int i_20_ = (i_18_ & 0xffd59b) >> 14; int i_21_ =
	 * i_19_ / 8 + (i_20_ / 8 << 8); for (int i_22_ = 0; i_22_ < regions;
	 * i_22_++) { if (currentRegions[i_22_] == i_21_) { i_21_ = -1; break; } }
	 * if (i_21_ != -1) { currentRegions[regions] = i_21_; int i_23_ = i_21_ &
	 * 0xff; int i_24_ = i_21_ >> 8 & 0xff; currentLandscapeFiles[regions] =
	 * cacheIndex5 .getHash(linkRSStrings(new RSString[] { m,
	 * RSString.valueOf(i_24_), underScore, RSString.valueOf(i_23_) }));
	 * Client.currentMapFiles[regions] = cacheIndex5 .getHash(linkRSStrings(new
	 * RSString[] { Client.l, RSString.valueOf(i_24_), underScore,
	 * RSString.valueOf(i_23_) })); regions++; } } } } }
	 * Client.updateRegion(height, regionX, regionY, localX, localY); } else {
	 * int regionX = inputBuffer.getULEShortA(); int localX =
	 * inputBuffer.getULEShort(); int regionY = inputBuffer.getULEShort(); int
	 * regions = (packetSize - inputBuffer.index) / 16; xtea = new
	 * int[regions][4]; for (int region = 0; region < regions; region++) { for
	 * (int z = 0; z < 4; z++) xtea[region][z] = inputBuffer.getLEInt(); }
	 * boolean forceSend = false; if ((regionX / 8 == 48 || regionX / 8 == 49)
	 * && regionY / 8 == 48) forceSend = true; int height =
	 * inputBuffer.getUByteA(); if (regionX / 8 == 48 && regionY / 8 == 148)
	 * forceSend = true; int localY = inputBuffer.getULEShort();
	 * landscapeFileBuffer = new byte[regions][]; currentRegions = new
	 * int[regions]; Client.currentMapFiles = new int[regions];
	 * currentLandscapeFiles = new int[regions]; mapFileBuffer = new
	 * byte[regions][]; regions = 0; for (int x = (regionX - 6) / 8; (regionX +
	 * 6) / 8 >= x; x++) { for (int y = (regionY - 6) / 8; y <= (regionY + 6) /
	 * 8; y++) { int region = y + (x << 8); System.out.println(regionX + ":" +
	 * regionY + ":" + region); if (!forceSend || (y != 49 && y != 149 && y !=
	 * 147 && x != 50 && (x != 49 || y != 47))) { currentRegions[regions] =
	 * region; currentLandscapeFiles[regions] = cacheIndex5
	 * .getHash(linkRSStrings(new RSString[] { m, RSString.valueOf(x),
	 * underScore, RSString.valueOf(y) })); currentMapFiles[regions] =
	 * cacheIndex5 .getHash(linkRSStrings(new RSString[] { l,
	 * RSString.valueOf(x), underScore, RSString.valueOf(y) })); regions++; } }
	 * } Client.updateRegion(height, regionX, regionY, localX, localY); } }
	 */
	public static final void refreshMap() {
		int regionX = mapX; // 3094
		int regionY = mapY; // 3107
		int height = Editor.editedHeight;
		int regions = 1;

		xtea = new int[regions][4];
		for (int region = 0; region < regions; region++) {
			for (int z = 0; z < 4; z++)
				xtea[region][z] = 0;
		}

		landscapeFileBuffer = new byte[regions][];
		currentRegions = new int[regions];
		Client.currentMapFiles = new int[regions];
		currentLandscapeFiles = new int[regions];
		mapFileBuffer = new byte[regions][];
		int region = regionY + (regionX << 8);
		squareX = regionX;
		squareY = regionY;
		System.out.println(regionX+":"+regionY);
		currentRegions[0] = region;
		currentLandscapeFiles[0] = cacheIndex5.getHash(linkRSStrings(new RSString[] { m,
						RSString.valueOf(regionX), underScore,
						RSString.valueOf(regionY) }));
		currentMapFiles[0] = cacheIndex5.getHash(linkRSStrings(new RSString[] { l
				, RSString.valueOf(regionX), underScore,
				RSString.valueOf(regionY) }));
		Client.updateRegion(height, regionX, regionY);
		Main.getGUI().setTitle(GUI.title + " - Currently editing Map Square "+squareX+","+squareY);
	}

	static final synchronized void method486(int[] is, int i) {
		int i_2_ = 0;
		i -= 7;
		while (i_2_ < i) {
			is[i_2_++] = 0;
			is[i_2_++] = 0;
			is[i_2_++] = 0;
			is[i_2_++] = 0;
			is[i_2_++] = 0;
			is[i_2_++] = 0;
			is[i_2_++] = 0;
			is[i_2_++] = 0;
		}
		i += 7;
		while (i_2_ < i)
			is[i_2_++] = 0;
		method689(i);
	}

	static final void drawMenu() {
		int offsetX = menuOffsetX;
		int offsetY = menuOffsetY;
		int width = Client.menuWidth;
		int height = menuHeight;
		int color = 6116423;
		Graphics2D.fillRect(offsetX, offsetY, width, height, color);
		Graphics2D.fillRect(offsetX + 1, offsetY + 1, width - 2, 16, 0);
		Graphics2D.drawRect(offsetX + 1, offsetY + 18, width - 2, height - 19,
				0);
		Client.boldFont.drawString(chooseOption, offsetX + 3, offsetY + 14,
				color);
		int x = lastMouseX;
		int y = Client.lastMouseY;
		for (int id = 0; Client.menuActionCount > id; id++) {
			int offsetY2 = (Client.menuActionCount - (id + 1)) * 15 + offsetY
					+ 31;
			int color2 = 16777215;
			if (offsetX < x && width + offsetX > x && y > offsetY2 - 13
					&& offsetY2 + 3 > y)
				color2 = 16776960;
			Client.boldFont.drawString((menuActionName[id]), offsetX + 3,
					offsetY2, color2, true);
		}
	}

	static final void method750(int i, int y, int dummy, int z, int i_5_, int x,
			RSByteBuffer class3_sub12) {
		/*
		 * 		RSByteBuffer class3_sub12 = new RSByteBuffer(is);
		for (int i_11_ = 0; i_11_ < 4; i_11_++) {
			for (int i_12_ = 0; i_12_ < 64; i_12_++) {
				for (int i_13_ = 0; i_13_ < 64; i_13_++)
					Client.method750(i, i_13_ + i_5_, 0, i_11_, i_6_, i_7_
							+ i_12_, class3_sub12);
			}
		}
		 */
		try {
			if (x >= 0 && y >= 0 && x < 103 && y < 103) {
				tileSettings[z][x][y] = (byte) 0;
				for (; ; ) {
					int opcode = class3_sub12.getUByte();
					if (opcode == 0) {
						heightMod[z][x][y] = true;
						if (z == 0)
							tileHeight[0][x][y] = -Client.method678(i + y + 556238,
									x + 932731 + i_5_) * 8;
						else
							tileHeight[z][x][y] = (tileHeight[z - 1][x][y]) - 240;
						break;
					}
					if (opcode == 1) {
						heightMod[z][x][y] = false;
						int heightModifier = class3_sub12.getUByte();
						if (heightModifier == 1)
							heightModifier = 0;
						if (z != 0)
							tileHeight[z][x][y] = -(heightModifier * 8) + (tileHeight[z - 1][x][y]);
						else
							tileHeight[0][x][y] = -heightModifier * 8;
						break;
					}
					if (opcode <= 49) {
						underlayIds[z][x][y] = class3_sub12.getByte();
						underlayShapes[z][x][y] = (byte) ((opcode - 2) / 4);
						underlayRotations[z][x][y] = (byte) ((opcode + (dummy - 2)) & 3);
					} else if (opcode <= 81) {
						tileSettings[z][x][y] = (byte) (opcode - 49);
						//System.out.println(z+":"+x+":"+y+" = "+tileSettings[z][x][y]);
					} else {
						overlayIds[z][x][y] = (byte) (opcode - 81);
					}
				}
			} else {
				for (; ; ) {
					int i_10_ = class3_sub12.getUByte();
					if (i_10_ == 0)
						break;
					if (i_10_ == 1) {
						class3_sub12.getUByte();
						break;
					}
					if (i_10_ <= 49)
						class3_sub12.getUByte();
				}
			}
		} catch (Exception e) {
			System.out.println("Leanbow sucks");
		}
	}

	static final boolean method235(boolean bool, boolean bool_62_,
			Signlink class45, int i) {
		if (!bool_62_)
			return false;
		if (i > 0)
			musicCache = new Cache(i);
		return true;
	}

	static final void worldToScreen(Entity entity, int offset) {
		Client.getEntityScreenPos(entity.x, entity.y, offset);
	}

	static final int method231(byte[] is, int i) {
		return method181(0, i, is);
	}

	static Class method868(String string) {
		Class var_class;
		try {
			var_class = Class.forName(string);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
		return var_class;
	}

	static final boolean method113(int y, int hash, int x) {
		int uid = Client.sceneGraph.getUID(height, x, y, hash);
		int id = (hash & 0x1fffe08a) >> 14;
		if (uid == -1)
			return false;
		int type = uid & 0x1f;
		int direction = uid >> 6 & 0x3;
		if (type == 10 || type == 11 || type == 22) {
			ObjectDefinition objectDef = ObjectDefinition.forID(id);
			int sizeY;
			int sizeX;
			if (direction == 0 || direction == 2) {
				sizeX = objectDef.sizeX;
				sizeY = objectDef.sizeY;
			} else {
				sizeY = objectDef.sizeX;
				sizeX = objectDef.sizeY;
			}
			int flag = objectDef.walkToFlag;
			if (direction != 0)
				flag = (flag >> 4 - direction) + (flag << direction & 0xf);
			doWalkTo(true, y, sizeY, flag, 2, 0, sizeX, x,
					playerLocalX, 0, playerLocalY);
		} else
			doWalkTo(true, y, 0, 0, 2, direction, 0, x, playerLocalX,
					type + 1, playerLocalY);
		crossX = lastClickX;
		crossState = 2;
		Client.crossY = lastClickY;
		Client.crossIndex = 0;
		return true;
	}

	static final void getPlayer() {
		entityUpdateCount = 0;
		Client.localEntityCount = 0;
		for (int id = 0; Client.localEntityCount > id; id++) {
			int index = localEntityIndices[id];
			if (Client.localPlayers[index].lastUpdate != loopCycle)
				Client.localPlayers[index] = null;
		}
	}

	static final void method816(int i, CacheFile class28,
			CacheFileWorker class18_sub1, int i_5_) {
		UpdateServerNode node = new UpdateServerNode();
		node.fileWorker = class18_sub1;
		node.cache = class28;
		node.anInt1246 = i_5_;
		node.hash = (long) i;
		synchronized (Client.updateServerList) {
			Client.updateServerList.insertBack(node);
		}
		Client.method568();
	}

	static final void resetTitleScreen() {
		if (titleScreenLoaded) {
			someLeftSquare = null;
			anIntArray1238 = null;
			Client.rightFlameSprite = null;
			Client.anIntArray157 = null;
			titleButton = null;
			anIntArray558 = null;
			rightTorchGraphicsBuffer = null;
			leftFlameGraphicsBuffer = null;
			titleBox = null;
			Client.anIntArray396 = null;
			Client.someRightSquare = null;
			leftFlameSprite = null;
			logoGraphicsBuffer = null;
			anIntArray2057 = null;
			Client.anIntArray2160 = null;
			rightFlameGraphicsBuffer = null;
			underTitleBoxGraphicsBuffer = null;
			Client.anIntArray1637 = null;
			titleBoxGraphicsBuffer = null;
			anIntArray9 = null;
			Client.runes = null;
			leftTorchGraphicsBuffer = null;
			resetUpdateServerRequests(true);
			titleScreenLoaded = false;
		}
	}

	static final void threadSleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException interruptedexception) {
			/* empty */
		}
	}

	static final CacheFileWorker initCacheWorker(boolean clearEntryBuffer,
			boolean onDemand, int index, boolean clearChildBuffer) {
		clearChildBuffer = false;
		onDemand = false;
		clearEntryBuffer = false;
		CacheFile file = null;
		if (Client.cacheFile != null)
			file = new CacheFile(Client.cacheFile, indexFile[index], index,
					1000000);
		CacheFileWorker cfw = new CacheFileWorker(file, referenceCache, index,
				clearEntryBuffer, clearChildBuffer, onDemand);
		cfw.setIndexCrc(10);
		return cfw;
	}

	static final void setupUnderlayFetcher(FileSystem fetcher) {
		underlayFetcher = fetcher;
		GUI.selectShapeButton.setEnabled(true);
		GUI.btnSelectOverlay.setEnabled(true);
		GUI.selectTileButton.setEnabled(true);
	}

	static final void method238() {
		for (CustomObjectSpawn class3_sub4 = (CustomObjectSpawn) Client.customObjectSpawns
				.getFront(); class3_sub4 != null; class3_sub4 = (CustomObjectSpawn) Client.customObjectSpawns
				.getNext()) {
			if (class3_sub4.time == -1) {
				class3_sub4.anInt1189 = 0;
				method65(class3_sub4);
			} else
				class3_sub4.unlink();
		}
	}

	static final void method193(SceneGraph class64, CollisionMap class59,
			int i, int i_43_, int i_44_, int i_45_, int i_46_, int i_47_,
			int i_49_) {
		int i_50_ = tileHeight[i_44_][i_46_ + 1][i];
		int i_51_ = tileHeight[i_44_][i_46_][i];
		int i_52_ = tileHeight[i_44_][i_46_ + 1][i + 1];
		int i_53_ = tileHeight[i_44_][i_46_][i + 1];
		int i_54_ = i_50_ + i_51_ - (-i_52_ - i_53_) >> 2;
		int i_55_ = (i_47_ << 6) + i_49_;
		ObjectDefinition objectDef = ObjectDefinition.forID(i_45_);
		int i_56_ = i_46_ + 1073741824 - (-(i << 7) - (i_45_ << 14));
		if (objectDef.anInt1671 == 1)
			i_55_ += 256;
		//if (objectDef.hasActions == 0)
		//	i_56_ -= -2147483648;
		if (i_49_ == 22) {
			SceneModel class3_sub3_sub3;
			if (objectDef.animationId != -1 || (objectDef.childrenIDs != null))
				class3_sub3_sub3 = new AnimatedObject(i_45_, 22, i_47_, i_51_,
						i_50_, i_52_, i_53_, objectDef.animationId, true);
			else
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_, 22,
						i_50_, i_52_);
			class64.addGroundDecoration(i_43_, i_46_, i, i_54_,
					class3_sub3_sub3, i_56_, i_55_);
			if (objectDef.isSolid && objectDef.hasActions == 1)
				class59.addGroundObjectsClip(i_46_, i);
		} else if (i_49_ == 10 || i_49_ == 11) {
			SceneModel class3_sub3_sub3;
			if (objectDef.animationId == -1 && (objectDef.childrenIDs == null))
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_, 10,
						i_50_, i_52_);
			else
				class3_sub3_sub3 = new AnimatedObject(i_45_, 10, i_47_, i_51_,
						i_50_, i_52_, i_53_, objectDef.animationId, true);
			if (class3_sub3_sub3 != null) {
				int i_57_ = 0;
				if (i_49_ == 11)
					i_57_ += 256;
				int sizeX;
				int sizeY;
				if (i_47_ == 1 || i_47_ == 3) {
					sizeX = objectDef.sizeY;
					sizeY = objectDef.sizeX;
				} else {
					sizeX = objectDef.sizeX;
					sizeY = objectDef.sizeY;
				}
				class64.addInteractiveObject(i_43_, i_46_, i, i_54_, sizeX,
						sizeY, class3_sub3_sub3, i_57_, i_56_, i_55_);
			}
			if (objectDef.isSolid)
				class59.addClipping(i_46_, i, objectDef.sizeX, objectDef.sizeY,
						i_47_, objectDef.isWalkable);
		} else if (i_49_ >= 12) {
			SceneModel class3_sub3_sub3;
			if (objectDef.animationId != -1 || (objectDef.childrenIDs != null))
				class3_sub3_sub3 = new AnimatedObject(i_45_, i_49_, i_47_,
						i_51_, i_50_, i_52_, i_53_, objectDef.animationId, true);
			else
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_,
						i_49_, i_50_, i_52_);
			class64.addInteractiveObject(i_43_, i_46_, i, i_54_, 1, 1,
					class3_sub3_sub3, 0, i_56_, i_55_);
			if (objectDef.isSolid)
				class59.addClipping(i_46_, i, objectDef.sizeX, objectDef.sizeY,
						i_47_, objectDef.isWalkable);
		} else if (i_49_ == 0) {
			SceneModel class3_sub3_sub3;
			if (objectDef.animationId != -1 || (objectDef.childrenIDs != null))
				class3_sub3_sub3 = new AnimatedObject(i_45_, 0, i_47_, i_51_,
						i_50_, i_52_, i_53_, objectDef.animationId, true);
			else
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_, 0,
						i_50_, i_52_);
			class64.addWallObject(i_43_, i_46_, i, i_54_, class3_sub3_sub3,
					null, Client.anIntArray992[i_47_], 0, i_56_, i_55_);
			if (objectDef.isSolid)
				class59.addClipping(i_49_, i_46_, i, (objectDef.isWalkable),
						i_47_);
		} else if (i_49_ == 1) {
			SceneModel class3_sub3_sub3;
			if (objectDef.animationId != -1 || (objectDef.childrenIDs != null))
				class3_sub3_sub3 = new AnimatedObject(i_45_, 1, i_47_, i_51_,
						i_50_, i_52_, i_53_, objectDef.animationId, true);
			else
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_, 1,
						i_50_, i_52_);
			class64.addWallObject(i_43_, i_46_, i, i_54_, class3_sub3_sub3,
					null, anIntArray616[i_47_], 0, i_56_, i_55_);
			if (objectDef.isSolid)
				class59.addClipping(i_49_, i_46_, i, (objectDef.isWalkable),
						i_47_);
		} else if (i_49_ == 2) {
			int i_60_ = i_47_ + 1 & 0x3;
			SceneModel class3_sub3_sub3;
			SceneModel class3_sub3_sub3_61_;
			if (objectDef.animationId != -1 || (objectDef.childrenIDs != null)) {
				class3_sub3_sub3 = new AnimatedObject(i_45_, 2, i_47_ + 4,
						i_51_, i_50_, i_52_, i_53_, objectDef.animationId, true);
				class3_sub3_sub3_61_ = new AnimatedObject(i_45_, 2, i_60_,
						i_51_, i_50_, i_52_, i_53_, objectDef.animationId, true);
			} else {
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_ + 4,
						2, i_50_, i_52_);
				class3_sub3_sub3_61_ = objectDef.method336(i_51_, i_53_, i_60_,
						2, i_50_, i_52_);
			}
			class64.addWallObject(i_43_, i_46_, i, i_54_, class3_sub3_sub3,
					class3_sub3_sub3_61_, Client.anIntArray992[i_47_],
					Client.anIntArray992[i_60_], i_56_, i_55_);
			if (objectDef.isSolid)
				class59.addClipping(i_49_, i_46_, i, (objectDef.isWalkable),
						i_47_);
		} else if (i_49_ == 3) {
			SceneModel class3_sub3_sub3;
			if (objectDef.animationId == -1 && (objectDef.childrenIDs == null))
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_, 3,
						i_50_, i_52_);
			else
				class3_sub3_sub3 = new AnimatedObject(i_45_, 3, i_47_, i_51_,
						i_50_, i_52_, i_53_, objectDef.animationId, true);
			class64.addWallObject(i_43_, i_46_, i, i_54_, class3_sub3_sub3,
					null, anIntArray616[i_47_], 0, i_56_, i_55_);
			if (objectDef.isSolid)
				class59.addClipping(i_49_, i_46_, i, (objectDef.isWalkable),
						i_47_);
		} else if (i_49_ == 9) {
			SceneModel class3_sub3_sub3;
			if (objectDef.animationId != -1 || (objectDef.childrenIDs != null))
				class3_sub3_sub3 = new AnimatedObject(i_45_, i_49_, i_47_,
						i_51_, i_50_, i_52_, i_53_, objectDef.animationId, true);
			else
				class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, i_47_,
						i_49_, i_50_, i_52_);
			class64.addInteractiveObject(i_43_, i_46_, i, i_54_, 1, 1,
					class3_sub3_sub3, 0, i_56_, i_55_);
			if (objectDef.isSolid)
				class59.addClipping(i_46_, i, objectDef.sizeX, objectDef.sizeY,
						i_47_, objectDef.isWalkable);
		} else {
			if (objectDef.aBoolean1654) {
				if (i_47_ == 1) {
					int i_62_ = i_53_;
					i_53_ = i_52_;
					i_52_ = i_50_;
					i_50_ = i_51_;
					i_51_ = i_62_;
				} else if (i_47_ == 2) {
					int i_63_ = i_53_;
					i_53_ = i_50_;
					i_50_ = i_63_;
					i_63_ = i_52_;
					i_52_ = i_51_;
					i_51_ = i_63_;
				} else if (i_47_ == 3) {
					int i_64_ = i_53_;
					i_53_ = i_51_;
					i_51_ = i_50_;
					i_50_ = i_52_;
					i_52_ = i_64_;
				}
			}
			if (i_49_ == 4) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId != -1
						|| (objectDef.childrenIDs != null))
					class3_sub3_sub3 = new AnimatedObject(i_45_, 4, 0, i_51_,
							i_50_, i_52_, i_53_, (objectDef.animationId), true);
				else
					class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, 0, 4,
							i_50_, i_52_);
				class64.addWallDecoration(i_43_, i_46_, i, i_54_,
						class3_sub3_sub3, Client.anIntArray992[i_47_],
						i_47_ * 512, 0, 0, i_56_, i_55_);
			} else if (i_49_ == 5) {
				int i_65_ = class64.getWallObjectHash(i_46_, i, i_43_);
				int i_66_ = 16;
				if (i_65_ > 0)
					i_66_ = ObjectDefinition.forID(i_65_ >> 14 & 0x7fff).anInt1673;
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId == -1
						&& (objectDef.childrenIDs == null))
					class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, 0, 4,
							i_50_, i_52_);
				else
					class3_sub3_sub3 = new AnimatedObject(i_45_, 4, 0, i_51_,
							i_50_, i_52_, i_53_, (objectDef.animationId), true);
				class64.addWallDecoration(i_43_, i_46_, i, i_54_,
						class3_sub3_sub3, Client.anIntArray992[i_47_],
						i_47_ * 512, Client.anIntArray897[i_47_] * i_66_, i_66_
								* Client.anIntArray162[i_47_], i_56_, i_55_);
			} else if (i_49_ == 6) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId == -1
						&& (objectDef.childrenIDs == null))
					class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, 0, 4,
							i_50_, i_52_);
				else
					class3_sub3_sub3 = new AnimatedObject(i_45_, 4, 0, i_51_,
							i_50_, i_52_, i_53_, (objectDef.animationId), true);
				class64.addWallDecoration(i_43_, i_46_, i, i_54_,
						class3_sub3_sub3, 256, i_47_, 0, 0, i_56_, i_55_);
			} else if (i_49_ == 7) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId == -1
						&& (objectDef.childrenIDs == null))
					class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, 0, 4,
							i_50_, i_52_);
				else
					class3_sub3_sub3 = new AnimatedObject(i_45_, 4, 0, i_51_,
							i_50_, i_52_, i_53_, (objectDef.animationId), true);
				class64.addWallDecoration(i_43_, i_46_, i, i_54_,
						class3_sub3_sub3, 512, i_47_, 0, 0, i_56_, i_55_);
			} else if (i_49_ == 8) {
				SceneModel class3_sub3_sub3;
				if (objectDef.animationId == -1
						&& (objectDef.childrenIDs == null))
					class3_sub3_sub3 = objectDef.method336(i_51_, i_53_, 0, 4,
							i_50_, i_52_);
				else
					class3_sub3_sub3 = new AnimatedObject(i_45_, 4, 0, i_51_,
							i_50_, i_52_, i_53_, (objectDef.animationId), true);
				class64.addWallDecoration(i_43_, i_46_, i, i_54_,
						class3_sub3_sub3, 768, i_47_, 0, 0, i_56_, i_55_);
			}
		}
	}

	static final RSFont getFont(RSString class63, RSString class63_29_,
			FileSystem class18) {
		int i_30_ = class18.getHash(class63_29_);
		int i_31_ = class18.getHash(class63, i_30_);
		return Client.getFont(class18, i_30_, i_31_);
	}

	static final Timer constructTimer() {
		Timer timer;
		try {
			timer = (Timer) Class.forName("NanoTimer").newInstance();
		} catch (Throwable throwable) {
			return new MillisTimer();
		}
		return timer;
	}

	static final void method183(int i, int x, int i_10_, int id, int i_12_,
			int z, int type, int dir, int y) {
		CustomObjectSpawn class3_sub4 = null;
		for (CustomObjectSpawn class3_sub4_18_ = ((CustomObjectSpawn) Client.customObjectSpawns
				.getFront()); class3_sub4_18_ != null; class3_sub4_18_ = (CustomObjectSpawn) Client.customObjectSpawns
				.getNext()) {
			if (class3_sub4_18_.z == z && x == class3_sub4_18_.x
					&& class3_sub4_18_.y == y
					&& class3_sub4_18_.sceneGraphType == i) {
				class3_sub4 = class3_sub4_18_;
				break;
			}
		}
		if (class3_sub4 == null) {
			class3_sub4 = new CustomObjectSpawn();
			class3_sub4.sceneGraphType = i;
			class3_sub4.y = y;
			class3_sub4.z = z;
			class3_sub4.x = x;
			method65(class3_sub4);
			Client.customObjectSpawns.insertBack(class3_sub4);
		}
		class3_sub4.id = id;
		class3_sub4.anInt1203 = dir;
		class3_sub4.type = type;
		class3_sub4.time = i_10_;
		class3_sub4.anInt1189 = i_12_;
	}

	static final int method181(int i, int i_1_, byte[] is) {
		int i_4_ = -1;
		for (int i_5_ = i; i_1_ > i_5_; i_5_++)
			i_4_ = (i_4_ >>> 8 ^ anIntArray7[(i_4_ ^ is[i_5_]) & 0xff]);
		i_4_ ^= 0xffffffff;
		return i_4_;
	}

	static final IndexedSprite method705(int i, int i_146_, FileSystem class18) {
		if (!spriteExists(class18, i, i_146_))
			return null;
		return Client.constructIndexedSprite();
	}

	static final void processMenuClick() {
		if (Client.activeInterfaceType == 0) {
			int clickType = lastMouseAction;
			if (!Client.menuOpen) {
				if (clickType == 1 && (mouseButtons == 1) && Client.menuActionCount > 2)
					clickType = 2;
				if (clickType == 1 && Client.menuActionCount > 0)
					doAction(Client.menuActionCount - 1);
				if (clickType == 2 && Client.menuActionCount > 0)
					determineMenuSize();
			} else {
				if (clickType != 1) {
					int mouseX = lastMouseX;
					int mouseY = lastMouseY;
					if (mouseX < menuOffsetX - 10
							|| mouseX > (Client.menuWidth + menuOffsetX + 10)
							|| mouseY < menuOffsetY - 10
							|| mouseY > Client.menuHeight + 10 + menuOffsetY) {
						Client.menuOpen = false;
						if (menuType == 2)
							redrawChatbox = true;
						if (menuType == 1)
							Client.redrawTabArea = true;
					}
				}
				if (clickType == 1) {
					Client.menuOpen = false;
				}
				if (clickType == 2 && Client.menuActionCount > 0)
					determineMenuSize();
			}
		}
	}

	public static int zoom = 0;
	public static Object lock = new Object();
	public static int zoomModifier = 2000;

	static final void draw3DScreen() {
		flashEffectCycle++;
		// updatePlayers(true);
		// updatePlayers(false);
		// processStillGraphics();
		if (!updateCamera) {
			int yaw = cameraYaw & 0x7ff;
			int pitch = Client.cameraPitch;
			//if (Client.anInt960 / 256 > pitch) {
			//	pitch = Client.anInt960 / 256;
			//}
			if (shaking[4] && pitch < shakingPitchMod[4] + 128)
				pitch = shakingPitchMod[4] + 128;
			//zoom = pitch * 3 + 2000 + currentRotation * 100;
			zoom = 2000 + currentRotation * 100;
			method576(yaw, Client.playerRenderY, Client.playerRenderX, pitch,
					zoom, getTileHeight(playerRenderX, playerRenderY - 50, height));
		}
		int height;
		if (!updateCamera)
			height = Client.method54();
		else
			height = method375();
		int i_5_ = renderZ;
		int i_6_ = renderX;
		int i_7_ = renderY;
		int i_8_ = renderPitch;
		int i_9_ = renderYaw;
		for (int i_10_ = 0; i_10_ < 5; i_10_++) {
			if (shaking[i_10_]) {
				int i_11_ = (int) (((double) (shakingPower[i_10_] * 2 + 1) * Math
						.random()) - (double) shakingPower[i_10_] + (Math
						.sin((double) Client.shakingCycle[i_10_]
								* ((double) (Client.anIntArray1636[i_10_]) / 100.0)) * (double) shakingPitchMod[i_10_]));
				if (i_10_ == 0)
					renderX += i_11_;
				if (i_10_ == 1)
					renderZ += i_11_;
				if (i_10_ == 2)
					renderY += i_11_;
				if (i_10_ == 3)
					renderYaw = i_11_ + renderYaw & 0x7ff;
				if (i_10_ == 4) {
					renderPitch += i_11_;
					if (renderPitch < 128)
						renderPitch = 128;
					if (renderPitch > 383)
						renderPitch = 383;
				}
			}
		}
		initGameScreen();//TODO here
		Model.aBoolean2012 = true;
		Model.anInt1998 = 0;
		Model.anInt2015 = Client.lastMouseY;
		Model.anInt2019 = lastMouseX;
		Graphics2D.resetpixels();
		Client.sceneGraph.method963(Client.renderX, renderZ, renderY,
				renderPitch, renderYaw, height);
		Client.sceneGraph.resetInteractiveObject();
		// updateEntities();
		((TextureLoader) Rasterizer.textureInterface).animateTexture(cycleRate);
		drawGameScreen();
		// if (drawTextOnScreen && method826(false, true) == 0)
		// drawTextOnScreen = false;
		// if (drawTextOnScreen) {
		// initGameScreen();
		// Graphics2D.resetpixels();
		// Client.drawTextOnScreen(loadingPleaseWait, null, false);
		// }
		Client.drawGameScreenBuffer();
		objectScreenBuffer.initDrawingArea();
		Rasterizer.initOffsets(offsets);
		Graphics2D.resetpixels();
		Rasterizer.method284((GUI.objectRenderCanvas.getWidth() / 2), (GUI.objectRenderCanvas.getHeight() / 2) + 20);
		synchronized(lock) {
		int id = Editor.selectedLocationId;
		if (id != -1) {
			ObjectDefinition objDef = ObjectDefinition.forID(id);
			if (objDef != null && objDef.modelCached()) {
				if (objDef.object_model_ids != null) {
					int type = 10;
					if (objDef.modelTypes != null) {
						type = objDef.modelTypes[0];
					}
					Model completeModel = objDef.method339(type, true, Editor.rotation, true);
					if (completeModel != null) {
						int zoom = 600;
						int rotateX = 0;
						int rotateY = 0;
						int posX = ((zoom * Rasterizer.sineTable[rotateX]) >> 16);
						int posY = ((zoom * Rasterizer.cosineTable[rotateX]) >> 16);
						completeModel.method144(0, rotateY, 0, rotateX, 0, posX, posY);
					}
				}
				if (objDef.modelTypes != null) {
					smallFont.drawCenteredString(RSString.createRSString("Types "+Arrays.toString(objDef.modelTypes)), 50, 10, 16776960);
				}
			}
		}
		}
		Rasterizer.method296();
		try {
			objectScreenBuffer.drawGraphics(GUI.objectRenderCanvas.getGraphics(), 0, 0);
		} catch(Exception e) {
			GUI.objectRenderCanvas.repaint();
		}
		
		Client.renderX = i_6_;
		renderZ = i_5_;
		renderY = i_7_;
		renderPitch = i_8_;
		renderYaw = i_9_;
	}
	
	static int[] offsets;

	static final void updateAnimation(Entity entity) {
		entity.aBoolean1914 = false;
		if (entity.currentAnimation != -1) {
			Sequence sequence = Sequence.forID(entity.currentAnimation);
			entity.anInt1908++;
			if (sequence.frames.length > entity.anInt1870
					&& entity.anInt1908 > sequence.timer[entity.anInt1870]) {
				entity.anInt1908 = 1;
				entity.anInt1870++;
			}
			if (sequence.frames.length <= entity.anInt1870) {
				entity.anInt1870 = 0;
				entity.anInt1908 = 0;
			}
		}
		if (entity.currentGraphic != -1
				&& loopCycle >= entity.currentGraphicCycle) {
			if (entity.currentGraphicFrame < 0)
				entity.currentGraphicFrame = 0;
			int animId = SpotAnimation.forID(entity.currentGraphic).animationId;
			if (animId == -1)
				entity.currentGraphic = -1;
			else {
				Sequence sequence = Sequence.forID(animId);
				entity.currentGraphicFrameTime++;
				if (sequence.frames.length > entity.currentGraphicFrame
						&& sequence.timer[entity.currentGraphicFrame] < entity.currentGraphicFrameTime) {
					entity.currentGraphicFrameTime = 1;
					entity.currentGraphicFrame++;
				}
				if (sequence.frames.length <= entity.currentGraphicFrame
						&& entity.currentGraphicFrame < 0
						|| entity.currentGraphicFrame >= sequence.frames.length)
					entity.currentGraphic = -1;
			}
		}
		do {
			if (entity.currentAnimationId != -1 && entity.animationDelay <= 1) {
				Sequence sequence = Sequence.forID(entity.currentAnimationId);
				if (sequence.speedupType == 1 && entity.anInt1906 > 0
						&& entity.startCycle <= loopCycle
						&& loopCycle > entity.moveCycle) {
					entity.animationDelay = 1;
					break;
				}
			}
			if (entity.currentAnimationId != -1 && entity.animationDelay == 0) {
				Sequence sequence = Sequence.forID(entity.currentAnimationId);
				entity.currentAnimationFrameTime++;
				if (sequence.frames.length > entity.currentAnimationFrameId
						&& sequence.timer[entity.currentAnimationFrameId] < entity.currentAnimationFrameTime) {
					entity.currentAnimationFrameTime = 1;
					entity.currentAnimationFrameId++;
				}
				if (entity.currentAnimationFrameId >= sequence.frames.length) {
					entity.resetCycle++;
					entity.currentAnimationFrameId -= sequence.anInt1570;
					if (sequence.resetCycle <= entity.resetCycle)
						entity.currentAnimationId = -1;
					if (entity.currentAnimationFrameId < 0
							|| sequence.frames.length <= entity.currentAnimationFrameId)
						entity.currentAnimationId = -1;
				}
				entity.aBoolean1914 = sequence.aBoolean1593;
			}
			if (entity.animationDelay > 0)
				entity.animationDelay--;
		} while (false);
	}

	static final IndexedSprite[] getMultipleIndexedSprites(FileSystem class18,
			RSString class63, RSString class63_0_) {
		int i_1_ = class18.getHash(class63);
		int i_2_ = class18.getHash(class63_0_, i_1_);
		return fetchIndexedSprites(i_1_, i_2_, class18);
	}

	static final void method726() {
		fourthOpcode = -1;
		Client.thirdOpcode = -1;
		Client.menuActionCount = 0;
		packetOpcode = -1;
		packetSize = 0;
		Client.menuOpen = false;
		Client.timoutTimer = 0;
		previousOpcode = -1;
		systemUpdateTime = 0;
		flagX = 0;
		minimapBlackout = 0;
		Client.setGameState(30);
	}

	static final boolean handleKeyboardInput() {
		synchronized (keyboardHandler) {
			if (Client.anInt125 == keyCacheLen)
				return false;
			keyCode = pressedKeyCodeCache[keyCacheLen];
			keyChar = Client.pressedKeyCharCache[keyCacheLen];
			keyCacheLen = keyCacheLen + 1 & 0x7f;
			return true;
		}
	}

	static final void handleMouse() {
		synchronized (mouseHandler) {
			lastMouseDragAction = currenMouseAction2;
			lastMouseX = currentMouseX;
			lastMouseY = currentMouseY;
			lastMouseAction = currenMouseAction;
			lastClickX = currentClickX;
			lastClickY = currentClickY;
			lastClickTime = currentClickTime;
			currenMouseAction = 0;
		}
		synchronized(mouseWheel) {
			currentRotation = mouseWheel.rotation;
		}
	}

	static final void updateEntities() {
		Client.textCount = 0;
		for (int id = -1; id < Client.localNpcCount + localPlayerCount; id++) {
			Entity entity;
			if (id == -1)
				entity = myPlayer;
			else if (id >= localPlayerCount)
				entity = localNpcs[localNpcIndices[id - localPlayerCount]];
			else
				entity = Client.localPlayers[Client.playerIndices[id]];
			if (entity != null && entity.isVisible()) {
				if (entity instanceof Npc) {
					NpcDefinition npcDef = ((Npc) entity).npcDefinition;
					if (npcDef.childrenIDs != null)
						npcDef = npcDef.handleNpcConfig();
					if (npcDef == null)
						continue;
				}
				if (id >= localPlayerCount) {
					NpcDefinition npcDef = ((Npc) entity).npcDefinition;
					if (npcDef.headIcon >= 0
							&& Client.prayerIcon.length > npcDef.headIcon) {
						Client.worldToScreen(entity, entity.height + 15);
						if (spriteDrawX > -1)
							Client.prayerIcon[npcDef.headIcon].drawSprite(
									spriteDrawX - 12, spriteDrawY - 30);
					}
					if (Client.hintIconID == 1
							&& (localNpcIndices[id - localPlayerCount] == hintNpcID)
							&& loopCycle % 20 < 10) {
						Client.worldToScreen(entity, entity.height + 15);
						if (spriteDrawX > -1)
							Client.hintIcon[0].drawSprite(spriteDrawX - 12,
									spriteDrawY - 28);
					}
				} else {
					int height = 30;
					Player player = (Player) entity;
					if (player.prayerIcon != -1 || player.skullIcon != -1) {
						Client.worldToScreen(entity, entity.height + 15);
						if (spriteDrawX > -1) {
							if (player.prayerIcon != -1) {
								skullIcon[player.prayerIcon].drawSprite(
										spriteDrawX - 12, spriteDrawY - height);
								height += 25;
							}
							if (player.skullIcon != -1) {
								Client.prayerIcon[player.skullIcon].drawSprite(
										spriteDrawX - 12, spriteDrawY - height);
								height += 25;
							}
						}
					}
					if (id >= 0 && Client.hintIconID == 10
							&& (Client.playerIndices[id] == hintPlayerID)) {
						Client.worldToScreen(entity, entity.height + 15);
						if (spriteDrawX > -1)
							Client.hintIcon[1].drawSprite(spriteDrawX - 12,
									spriteDrawY - height);
					}
				}
				if (loopCycle < entity.hitCycle) {
					Client.worldToScreen(entity, entity.height + 15);
					if (spriteDrawX > -1) {
						int health = (entity.currentHealth * 30)
								/ entity.maxHealth;
						if (health > 30)
							health = 30;
						Graphics2D.fillRect(spriteDrawX - 15, spriteDrawY - 3,
								health, 5, 65280);
						Graphics2D.fillRect((spriteDrawX - 15) + health,
								spriteDrawY - 3, 30 - health, 5, 16711680);
					}
				}
				for (int hitId = 0; hitId < 4; hitId++) {
					if (loopCycle < entity.hitCycles[hitId]) {
						Client.worldToScreen(entity, entity.height / 2);
						if (spriteDrawX > -1) {
							if (hitId == 1)
								spriteDrawY -= 20;
							if (hitId == 2) {
								spriteDrawY -= 10;
								spriteDrawX -= 15;
							}
							if (hitId == 3) {
								spriteDrawY -= 10;
								spriteDrawX += 15;
							}
							Client.hitMarks[entity.hitMarkTypes[hitId]]
									.drawSprite(spriteDrawX - 12,
											spriteDrawY - 12);
							smallFont.drawCenteredString(
									RSString.valueOf(entity.hitValues[hitId]),
									spriteDrawX, spriteDrawY + 4, 0);
							smallFont.drawCenteredString(
									RSString.valueOf(entity.hitValues[hitId]),
									spriteDrawX - 1, spriteDrawY + 3, 16777215);
						}
					}
				}
			}
		}
		for (int id = 0; Client.textCount > id; id++) {
			int textY = textSpokenY[id];
			int textWidth = textSpokenWidth[id];
			int textX = textSpokenX[id];
			int textHeight = textSpokenHeight[id];
			boolean bool = true;
			while (bool) {
				bool = false;
				for (int i_10_ = 0; id > i_10_; i_10_++) {
					if ((textSpokenY[i_10_] - textSpokenHeight[i_10_]) < textY + 2
							&& textY - textHeight < textSpokenY[i_10_] + 2
							&& textX - textWidth < (textSpokenX[i_10_] + textSpokenWidth[i_10_])
							&& textWidth + textX > (textSpokenX[i_10_] - textSpokenWidth[i_10_])
							&& (textSpokenY[i_10_] - textSpokenHeight[i_10_]) < textY) {
						bool = true;
						textY = (textSpokenY[i_10_] - textSpokenHeight[i_10_]);
					}
				}
			}
			spriteDrawX = textSpokenX[id];
			spriteDrawY = textSpokenY[id] = textY;
			RSString message = textSpokenMessage[id];
			if (chatEffects == 0) {
				int color = 16776960;
				if (textSpokenColor[id] < 6)
					color = chatColors[textSpokenColor[id]];
				if (textSpokenColor[id] == 6)
					color = flashEffectCycle % 20 < 10 ? 16711680 : 16776960;
				if (textSpokenColor[id] == 7)
					color = flashEffectCycle % 20 < 10 ? 255 : 65535;
				if (textSpokenColor[id] == 8)
					color = flashEffectCycle % 20 >= 10 ? 8454016 : 45056;
				if (textSpokenColor[id] == 9) {
					int cycle = 150 - textSpokenCycle[id];
					if (cycle < 50)
						color = cycle * 1280 + 16711680;
					else if (cycle >= 100) {
						if (cycle < 150)
							color = (cycle - 100) * 5 + 65280;
					} else
						color = 16776960 - (cycle - 50) * 327680;
				}
				if (textSpokenColor[id] == 10) {
					int cycle = 150 - textSpokenCycle[id];
					if (cycle < 50)
						color = cycle * 5 + 16711680;
					else if (cycle >= 100) {
						if (cycle < 150)
							color = -32768000
									- (-(cycle * 327680) - 255 - 500 + cycle * 5);
					} else
						color = 16711935 - ((cycle - 50) * 327680);
				}
				if (textSpokenColor[id] == 11) {
					int cycle = 150 - textSpokenCycle[id];
					if (cycle >= 50) {
						if (cycle < 100)
							color = (cycle - 50) * 327685 + 65280;
						else if (cycle < 150)
							color = 16777215 - (cycle * 327680 - 32768000);
					} else
						color = 16777215 - (cycle * 327685);
				}
				if (textSpokenEffect[id] == 0) {
					Client.boldFont.drawCenteredString(message, spriteDrawX,
							spriteDrawY + 1, 0);
					Client.boldFont.drawCenteredString(message, spriteDrawX,
							spriteDrawY, color);
				}
				if (textSpokenEffect[id] == 1) {
					Client.boldFont.drawWaveText(message, spriteDrawX,
							spriteDrawY + 1, 0, flashEffectCycle);
					Client.boldFont.drawWaveText(message, spriteDrawX,
							spriteDrawY, color, flashEffectCycle);
				}
				if (textSpokenEffect[id] == 2) {
					Client.boldFont.drawWaveText2(message, spriteDrawX,
							spriteDrawY + 1, 0, flashEffectCycle);
					Client.boldFont.drawWaveText2(message, spriteDrawX,
							spriteDrawY, color, flashEffectCycle);
				}
				if (textSpokenEffect[id] == 3) {
					Client.boldFont.drawShakeText(message, spriteDrawX,
							spriteDrawY + 1, 0, flashEffectCycle,
							150 - textSpokenCycle[id]);
					Client.boldFont.drawShakeText(message, spriteDrawX,
							spriteDrawY, color, flashEffectCycle,
							-textSpokenCycle[id] + 150);
				}
				if (textSpokenEffect[id] == 4) {
					int width = Client.boldFont.getStringWidth(message);
					int cycle = ((width + 100) * (150 - textSpokenCycle[id]) / 150);
					Graphics2D.setBounds(spriteDrawX - 50, 0, spriteDrawX + 50,
							334);
					Client.boldFont.drawString(message, spriteDrawX - cycle
							+ 50, spriteDrawY + 1, 0);
					Client.boldFont.drawString(message, spriteDrawX + 50
							- cycle, spriteDrawY, color);
					Graphics2D.resetBounds();
				}
				if (textSpokenEffect[id] == 5) {
					int cycle = 150 - textSpokenCycle[id];
					Graphics2D.setBounds(0, (-Client.boldFont.trimHeight
							+ spriteDrawY - 1), 512, spriteDrawY + 5);
					int y = 0;
					if (cycle >= 25) {
						if (cycle > 125)
							y = cycle - 125;
					} else
						y = cycle - 25;
					Client.boldFont.drawCenteredString(message, spriteDrawX, y
							+ (spriteDrawY + 1), 0);
					Client.boldFont.drawCenteredString(message, spriteDrawX, y
							+ spriteDrawY, color);
					Graphics2D.resetBounds();
				}
			} else {
				Client.boldFont.drawCenteredString(message, spriteDrawX,
						spriteDrawY + 1, 0);
				Client.boldFont.drawCenteredString(message, spriteDrawX,
						spriteDrawY, 16776960);
			}
		}
	}

	static final void setupObjectFetcher(FileSystem class18, boolean bool,
			FileSystem class18_20_) {
		Client.objectModelsFetcher = class18;
		Client.objectLowMem = bool;
		Client.objectFetcher = class18_20_;
		Client.totalObjects = 57264;
		for (int id = 0; id < Client.totalObjects; id++) {
			ObjectDefinition.forID(id);
		}
	}
	
	public static int totalObjects;
	public static int totalResults;

	static final void method170() {
		/*anIntArray192 = null;
		Client.groundSaturations = null;
		Client.underlayIds = null;
		Client.groundsLigtness = null;
		groundHues = null;
		overlayIds = null;
		overlayShape = null;
		aByteArrayArrayArray12 = null;
		overlayRotation = null;
		Client.tileBrightness = null;
		anIntArray574 = null;
		anIntArrayArrayArray438 = null;*/
	}

	static final void setupSpotAnimtionFetcher(FileSystem class18,
			FileSystem class18_19_) {
		Client.spotAnimationFetcher = class18_19_;
		spotAnimationModelFetcher = class18;
	}

	static final void removeKeyboard(Component component) {
		component.removeKeyListener(keyboardHandler);
		component.removeFocusListener(keyboardHandler);
	}

	static final void method576(int yaw, int pRY, int pRX, int pitch, int zoom,
			int height) {
		int pitchDiff = 2048 - pitch & 0x7ff;
		int yawDiff = 2048 - yaw & 0x7ff;
		int i_56_ = 0;
		int i_57_ = 0;
		int i_58_ = zoom;
		if (pitchDiff != 0) {
			int pitchCosine = Model.cosine[pitchDiff];
			int pitchSine = Model.sine[pitchDiff];
			int i_61_ = i_57_ * pitchCosine - i_58_ * pitchSine >> 16;
			i_58_ = i_57_ * pitchSine + pitchCosine * i_58_ >> 16;
			i_57_ = i_61_;
		}
		if (yawDiff != 0) {
			int yawSine = Model.sine[yawDiff];
			int yawCosine = Model.cosine[yawDiff];
			int i_64_ = yawCosine * i_56_ + yawSine * i_58_ >> 16;
			i_58_ = -(i_56_ * yawSine) + i_58_ * yawCosine >> 16;
			i_56_ = i_64_;
		}
		renderZ = height - i_57_;
		renderX = pRX - i_56_;
		renderY = pRY - i_58_;
		renderYaw = yaw;
		renderPitch = pitch;
	}

	static List<Integer> overlays = new CopyOnWriteArrayList<Integer>();
	static List<Integer> underlays = new CopyOnWriteArrayList<Integer>();

	public static final void method599(CollisionMap[] class59s, SceneGraph class64) {
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 104; x++) {
				for (int y = 0; y < 104; y++) {
					if ((Client.tileSettings[z][x][y] & 0x1) == 1) {
						int z_ = z;
						if ((Client.tileSettings[1][x][y] & 0x2) == 2)
							z_--;
						if (z_ >= 0)
							class59s[z_].addGroundObjectsClip(x, y);
					}
				}
			}
		}
		for (int z = 0; z < 4; z++) {
			byte[][] is = aByteArrayArrayArray12[z];
			int i_17_ = (int) Math.sqrt(5100.0);
			int i_18_ = i_17_ * 768 >> 8;
			for (int y = 1; y < 104; y++) {
				for (int x = 1; x < 104; x++) {
					int i_21_ = tileHeight[z][x + 1][y] - tileHeight[z][x - 1][y];
					int i_22_ = tileHeight[z][x][y + 1] - tileHeight[z][x][y - 1];
					int i_23_ = (int) Math.sqrt((double) (i_21_ * i_21_ + i_22_
							* i_22_ + 65536));
					int i_24_ = 65536 / i_23_;
					int i_25_ = (i_21_ << 8) / i_23_;
					int i_26_ = (i_22_ << 8) / i_23_;
					int i_27_ = 96
							+ (i_25_ * -50 + (i_24_ * -10 + i_26_ * -50))
							/ i_18_;
					int i_28_ = ((is[x][y] >> 1) + (is[x + 1][y] >> 3)
							+ ((is[x - 1][y] >> 2) + (is[x][y - 1] >> 2)) + (is[x][y + 1] >> 3));
					Client.tileBrightness[x][y] = i_27_ - i_28_;
				}
			}
			for (int i_29_ = 0; i_29_ < 104; i_29_++) {
				groundHues[i_29_] = 0;
				Client.groundSaturations[i_29_] = 0;
				Client.groundsLigtness[i_29_] = 0;
				groundAlphas[i_29_] = 0;
				anIntArray574[i_29_] = 0;
			}
			for (int x = -5; x < 109; x++) {
				for (int i_31_ = 0; i_31_ < 104; i_31_++) {
					int i_32_ = x + 5;
					if (i_32_ >= 0 && i_32_ < 104) {
						int i_33_ = (Client.overlayIds[z][i_32_][i_31_] & 0xff);
						if (i_33_ > 0) {
							OverLayFloor class3_sub3_sub5 = OverLayFloor
									.forID(i_33_ - 1);
							groundHues[i_31_] += class3_sub3_sub5.groundHue;
							Client.groundSaturations[i_31_] += class3_sub3_sub5.groundSaturation;
							Client.groundsLigtness[i_31_] += class3_sub3_sub5.groundLightness;
							groundAlphas[i_31_] += class3_sub3_sub5.alpha;
							anIntArray574[i_31_]++;
						}
					}
					int i_34_ = x - 5;
					if (i_34_ >= 0 && i_34_ < 104) {
						int i_35_ = (Client.overlayIds[z][i_34_][i_31_] & 0xff);
						if (i_35_ > 0) {
							OverLayFloor class3_sub3_sub5 = OverLayFloor
									.forID(i_35_ - 1);
							groundHues[i_31_] -= class3_sub3_sub5.groundHue;
							Client.groundSaturations[i_31_] -= class3_sub3_sub5.groundSaturation;
							Client.groundsLigtness[i_31_] -= class3_sub3_sub5.groundLightness;
							groundAlphas[i_31_] -= class3_sub3_sub5.alpha;
							anIntArray574[i_31_]--;
						}
					}
				}
				if (x >= 0 && x < 103) {
					int hue = 0;
					int saturation = 0;
					int lightness = 0;
					int alpha = 0;
					int i_40_ = 0;
					for (int y = -5; y < 109; y++) {
						int i_42_ = y + 5;
						if (i_42_ >= 0 && i_42_ < 104) {
							lightness += Client.groundsLigtness[i_42_];
							hue += groundHues[i_42_];
							alpha += groundAlphas[i_42_];
							i_40_ += anIntArray574[i_42_];
							saturation += Client.groundSaturations[i_42_];
						}
						int i_43_ = y - 5;
						if (i_43_ >= 0 && i_43_ < 104) {
							i_40_ -= anIntArray574[i_43_];
							alpha -= groundAlphas[i_43_];
							lightness -= Client.groundsLigtness[i_43_];
							hue -= groundHues[i_43_];
							saturation -= Client.groundSaturations[i_43_];
						}
						if (y >= 0
								&& y < 103
								&& (!Client.lowMem
										|| ((Client.tileSettings[0][x][y]) & 0x2) != 0 || (((Client.tileSettings[z][x][y]) & 0x10) == 0 && (method537(
										z, x, y) == oldHeight)))) {
							if (z < Client.anInt1332)
								Client.anInt1332 = z;
							if(x == 24 && y == 45) {
								if(((underlayIds[z][x][y]) & 0xff) != 0)
									System.out.println((underlayIds[z][x][y]) & 0xff);
								if(((overlayIds[z][x][y]) & 0xff) != 0)
									System.out.println((overlayIds[z][x][y]) & 0xff);
							}
							int i_44_ = ((Client.overlayIds[z][x][y]) & 0xff);
							int i_45_ = ((underlayIds[z][x][y]) & 0xff);
							if (i_44_ > 0 || i_45_ > 0) {
								int i_46_ = (tileHeight[z][x + 1][y]);
								int i_47_ = (tileHeight[z][x + 1][y + 1]);
								int i_48_ = (tileHeight[z][x][y + 1]);
								int i_49_ = (Client.tileBrightness[x][y]);
								int i_50_ = (tileHeight[z][x][y]);
								int i_51_ = (Client.tileBrightness[x][y + 1]);
								int i_52_ = (Client.tileBrightness[x + 1][y + 1]);
								int i_53_ = -1;
								int i_54_ = -1;
								int i_55_ = (Client.tileBrightness[x + 1][y]);
								if (i_44_ > 0) {
									int i_56_;
									int i_57_;
									int i_58_;
									if(Editor.disableTileBlending) {
										OverLayFloor class3_sub3_sub5 = OverLayFloor.forID(i_44_ - 1);
										i_56_ = class3_sub3_sub5.groundHue * 256 / class3_sub3_sub5.alpha;
										i_57_ = class3_sub3_sub5.groundSaturation;
										i_58_ = class3_sub3_sub5.groundLightness;
									} else {
										i_56_ = hue * 256 / alpha;
										i_57_ = saturation / i_40_;
										i_58_ = lightness / i_40_;
									}
									i_54_ = (Client.method1005(i_56_, i_57_,
											i_58_));
									if (i_58_ < 0)
										i_58_ = 0;
									else if (i_58_ > 255)
										i_58_ = 255;
									i_53_ = (Client.method1005(i_56_, i_57_,
											i_58_));
								}
								if (z > 0) {
									boolean bool = true;
									if (i_44_ == 0
											&& underlayShapes[z][x][y] != 0)
										bool = false;
									if (i_45_ > 0 && !UnderLayFloor.forID(i_45_ - 1).aBoolean1429) {
										bool = false;
									}
									if (bool && i_46_ == i_50_
											&& i_47_ == i_50_ && i_48_ == i_50_) {
										anIntArrayArrayArray438[z][x][y] |= 1170;
									}
								}
								int color = 0;
								if (i_53_ != -1)
									color = (Rasterizer.palette[Client
											.blendOverlayColor(96, i_53_)]);
								if (i_45_ != 0) {
									byte rotation = (underlayRotations[z][x][y]);
									int shape = ((underlayShapes[z][x][y]) + 1);
									UnderLayFloor class3_sub3_sub1 = UnderLayFloor
											.forID(i_45_ - 1);
									int textureId = (class3_sub3_sub1.textureId);
									int i_63_;
									int i_64_;
									if (textureId >= 0 && textureId <= 51) {
										i_63_ = -1;
										i_64_ = Rasterizer.textureInterface
												.getTextureId(textureId);
									} else if (class3_sub3_sub1.anInt1426 != 16711935) {
										i_63_ = (Client.method1005(
												class3_sub3_sub1.hue,
												class3_sub3_sub1.saturation,
												class3_sub3_sub1.lightness));
										int i_65_ = ((class3_sub3_sub1.hue) & 0xff);
										int i_66_ = (class3_sub3_sub1.lightness);
										if (i_66_ < 0)
											i_66_ = 0;
										else if (i_66_ > 255)
											i_66_ = 255;
										i_64_ = (Client.method1005(
												i_65_,
												class3_sub3_sub1.saturation, i_66_));
									} else {
										i_63_ = -2;
										i_64_ = -2;
										textureId = -1;
									}
									int color2 = 0;
									if (i_64_ != -2)
										color2 = (Rasterizer.palette[(blendUnderlayColor(
												96, i_64_))]);
									if ((class3_sub3_sub1.anInt1420) != -1) {
										int i_68_ = ((class3_sub3_sub1.anInt1422) & 0xff);
										int i_69_ = (class3_sub3_sub1.anInt1421);
										if (i_69_ < 0)
											i_69_ = 0;
										else if (i_69_ > 255)
											i_69_ = 255;
										i_64_ = (Client.method1005(
												i_68_,
												class3_sub3_sub1.anInt1434, i_69_));
										color2 = (Rasterizer.palette[(blendUnderlayColor(
												96, i_64_))]);
									}
									class64.method988(z, x, y,
											textureId, shape, rotation, i_50_,
											i_46_, i_47_, i_48_,
											Client.blendOverlayColor(i_49_, i_54_),
											Client.blendOverlayColor(i_55_, i_54_),
											Client.blendOverlayColor(i_51_, i_54_),
											Client.blendOverlayColor(i_52_, i_54_),
											blendUnderlayColor(i_49_, i_63_),
											blendUnderlayColor(i_55_, i_63_),
											blendUnderlayColor(i_52_, i_63_),
											blendUnderlayColor(i_51_, i_63_), color,
											color2);
								} else
									class64.method988(z, x, y, -1, 0,
											0, i_50_, i_46_, i_47_, i_48_,
											Client.blendOverlayColor(i_49_, i_54_),
											(Client.blendOverlayColor(i_55_, i_54_)),
											Client.blendOverlayColor(i_51_, i_54_),
											Client.blendOverlayColor(i_52_, i_54_), 0,
											0, 0, 0, color, 0);
							}
						}
					}
				}
			}
			for (int i_70_ = 0; i_70_ < 104; i_70_++) {
				for (int i_71_ = 0; i_71_ < 104; i_71_++)
					class64.method949(z, i_71_, i_70_, method537(z, i_71_, i_70_));
			}
			/*Client.underlayIds[z] = null;
			overlayIds[z] = null;
			overlayShape[z] = null;
			overlayRotation[z] = null;
			aByteArrayArrayArray12[z] = null;*/
		}
		class64.method985(-50, -10, -50);
		for (int i_72_ = 0; i_72_ < 104; i_72_++) {
			for (int i_73_ = 0; i_73_ < 104; i_73_++) {
				if (((Client.tileSettings[1][i_72_][i_73_]) & 0x2) == 2)
					class64.method978(i_72_, i_73_);
			}
		}
		int i_74_ = 1;
		int i_75_ = 2;
		int i_76_ = 4;
		for (int i_77_ = 0; i_77_ < 4; i_77_++) {
			if (i_77_ > 0) {
				i_75_ <<= 3;
				i_74_ <<= 3;
				i_76_ <<= 3;
			}
			for (int i_78_ = 0; i_77_ >= i_78_; i_78_++) {
				for (int i_79_ = 0; i_79_ <= 104; i_79_++) {
					for (int i_80_ = 0; i_80_ <= 104; i_80_++) {
						if ((i_74_ & (anIntArrayArrayArray438[i_78_][i_80_][i_79_])) != 0) {
							int i_81_ = i_79_;
							int i_82_ = i_79_;
							for (; i_81_ > 0
									&& ((anIntArrayArrayArray438[i_78_][i_80_][i_81_ - 1]) & i_74_) != 0; i_81_--) {
							}
							int i_83_ = i_78_;
							for (; (i_82_ < 104 && ((anIntArrayArrayArray438[i_78_][i_80_][i_82_ + 1]) & i_74_) != 0); i_82_++) {
							}
							int i_84_ = i_78_;
							while_0_: for (; i_83_ > 0; i_83_--) {
								for (int i_85_ = i_81_; i_85_ <= i_82_; i_85_++) {
									if (((anIntArrayArrayArray438[i_83_ - 1][i_80_][i_85_]) & i_74_) == 0)
										break while_0_;
								}
							}
							while_1_: for (; i_77_ > i_84_; i_84_++) {
								for (int i_86_ = i_81_; i_82_ >= i_86_; i_86_++) {
									if ((i_74_ & (anIntArrayArrayArray438[i_84_ + 1][i_80_][i_86_])) == 0)
										break while_1_;
								}
							}
							int i_87_ = ((-i_83_ + (i_84_ + 1)) * (-i_81_
									+ i_82_ + 1));
							if (i_87_ >= 8) {
								int i_88_ = 240;
								int i_89_ = (tileHeight[i_83_][i_80_][i_81_]);
								int i_90_ = -i_88_
										+ (tileHeight[i_84_][i_80_][i_81_]);
								SceneGraph.method964(i_77_, 1, i_80_ * 128,
										i_80_ * 128, i_81_ * 128,
										i_82_ * 128 + 128, i_90_, i_89_);
								for (int i_91_ = i_83_; i_84_ >= i_91_; i_91_++) {
									for (int i_92_ = i_81_; i_82_ >= i_92_; i_92_++)
										anIntArrayArrayArray438[i_91_][i_80_][i_92_] = (anIntArrayArrayArray438[i_91_][i_80_][i_92_] & (i_74_ ^ 0xffffffff));
								}
							}
						}
						if (((anIntArrayArrayArray438[i_78_][i_80_][i_79_]) & i_75_) != 0) {
							int i_93_;
							for (i_93_ = i_80_; (i_93_ > 0 && ((i_75_ & (anIntArrayArrayArray438[i_78_][i_93_ - 1][i_79_])) != 0)); i_93_--) {
							}
							int i_94_ = i_80_;
							int i_95_ = i_78_;
							for (; (i_94_ < 104 && ((i_75_ & (anIntArrayArrayArray438[i_78_][i_94_ + 1][i_79_])) != 0)); i_94_++) {
							}
							int i_96_ = i_78_;
							while_2_: for (; i_95_ > 0; i_95_--) {
								for (int i_97_ = i_93_; i_97_ <= i_94_; i_97_++) {
									if ((i_75_ & (anIntArrayArrayArray438[i_95_ - 1][i_97_][i_79_])) == 0)
										break while_2_;
								}
							}
							while_3_: for (; i_96_ < i_77_; i_96_++) {
								for (int i_98_ = i_93_; i_98_ <= i_94_; i_98_++) {
									if ((i_75_ & (anIntArrayArrayArray438[i_96_ + 1][i_98_][i_79_])) == 0)
										break while_3_;
								}
							}
							int i_99_ = (i_94_ - i_93_ + 1)
									* (i_96_ + (1 - i_95_));
							if (i_99_ >= 8) {
								int i_100_ = 240;
								int i_101_ = ((tileHeight[i_96_][i_93_][i_79_]) - i_100_);
								int i_102_ = (tileHeight[i_95_][i_93_][i_79_]);
								SceneGraph.method964(i_77_, 2, i_93_ * 128,
										i_94_ * 128 + 128, i_79_ * 128,
										i_79_ * 128, i_101_, i_102_);
								for (int i_103_ = i_95_; i_96_ >= i_103_; i_103_++) {
									for (int i_104_ = i_93_; i_94_ >= i_104_; i_104_++)
										anIntArrayArrayArray438[i_103_][i_104_][i_79_] = (anIntArrayArrayArray438[i_103_][i_104_][i_79_] & (i_75_ ^ 0xffffffff));
								}
							}
						}
						if (((anIntArrayArrayArray438[i_78_][i_80_][i_79_]) & i_76_) != 0) {
							int i_105_ = i_80_;
							int i_106_;
							for (i_106_ = i_79_; (i_106_ > 0 && ((anIntArrayArrayArray438[i_78_][i_80_][i_106_ - 1]) & i_76_) != 0); i_106_--) {
							}
							int i_107_ = i_80_;
							int i_108_;
							for (i_108_ = i_79_; i_108_ < 104; i_108_++) {
								if ((i_76_ & (anIntArrayArrayArray438[i_78_][i_80_][i_108_ + 1])) == 0)
									break;
							}
							while_4_: for (; i_107_ > 0; i_107_--) {
								for (int i_109_ = i_106_; i_108_ >= i_109_; i_109_++) {
									if (((anIntArrayArrayArray438[i_78_][i_107_ - 1][i_109_]) & i_76_) == 0)
										break while_4_;
								}
							}
							while_5_: for (; i_105_ < 104; i_105_++) {
								for (int i_110_ = i_106_; i_108_ >= i_110_; i_110_++) {
									if (((anIntArrayArrayArray438[i_78_][i_105_ + 1][i_110_]) & i_76_) == 0)
										break while_5_;
								}
							}
							if ((i_105_ - i_107_ + 1) * (i_108_ + 1 - i_106_) >= 4) {
								int i_111_ = (tileHeight[i_78_][i_107_][i_106_]);
								SceneGraph.method964(i_77_, 4, i_107_ * 128,
										i_105_ * 128 + 128, i_106_ * 128,
										i_108_ * 128 + 128, i_111_, i_111_);
								for (int i_112_ = i_107_; i_112_ <= i_105_; i_112_++) {
									for (int i_113_ = i_106_; i_113_ <= i_108_; i_113_++)
										anIntArrayArrayArray438[i_78_][i_112_][i_113_] = (anIntArrayArrayArray438[i_78_][i_112_][i_113_] & (i_76_ ^ 0xffffffff));
								}
							}
						}
					}
				}
			}
		}
	}
	
	static final void setupVarpFetcher(FileSystem class18) {
		varpFetcher = class18;
		varpLen = varpFetcher.getChildCount(16);
	}

	static final boolean doWalkTo(boolean moveNear, int destY, int ySize,
			int flag, int walkType, int direction, int xSize, int destX,
			int posX, int objType, int posY) {
		for (int x_ = 0; x_ < 104; x_++) {
			for (int y_ = 0; y_ < 104; y_++) {
				via[x_][y_] = 0;
				cost[x_][y_] = 99999999;
			}
		}
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 104; x++) {
				for (int y = 0; y < 104; y++)
					Client.collisionMaps[z].collisionFlags[x][y] = 0;
			}
		}
		via[posX][posY] = 99;
		cost[posX][posY] = 0;
		int curX = posX;
		int pathLen = 0;
		bigX[pathLen] = posX;
		int curY = posY;
		int tail = 0;
		bigY[pathLen++] = posY;
		boolean foundPath = false;
		int steps = bigX.length;
		int[][] clipMask = Client.collisionMaps[height].collisionFlags;
		while (tail != pathLen) {
			curY = bigY[tail];
			curX = bigX[tail];
			tail = (tail + 1) % steps;
			if (curX == destX && destY == curY) {
				foundPath = true;
				break;
			}
			if (objType != 0) {
				if ((objType < 5 || objType == 10)
						&& Client.collisionMaps[height].reachedWall(curY,
								direction, curX, objType - 1, destX, destY)) {
					foundPath = true;
					break;
				}
				if (objType < 10
						&& (Client.collisionMaps[height].reachedDiagonalWall(
								direction, curX, destX, destY, curY,
								objType - 1))) {
					foundPath = true;
					break;
				}
			}
			if (xSize != 0
					&& ySize != 0
					&& (Client.collisionMaps[height].reachedDistance(destY,
							xSize, ySize, curY, curX, destX, flag))) {
				foundPath = true;
				break;
			}
			int end = cost[curX][curY] + 1;
			if (curX > 0 && via[curX - 1][curY] == 0
					&& (clipMask[curX - 1][curY] & 0x1280108) == 0) {
				bigX[pathLen] = curX - 1;
				bigY[pathLen] = curY;
				via[curX - 1][curY] = 2;
				pathLen = (pathLen + 1) % steps;
				cost[curX - 1][curY] = end;
			}
			if (curX < 103 && via[curX + 1][curY] == 0
					&& (clipMask[curX + 1][curY] & 0x1280180) == 0) {
				bigX[pathLen] = curX + 1;
				bigY[pathLen] = curY;
				via[curX + 1][curY] = 8;
				pathLen = (pathLen + 1) % steps;
				cost[curX + 1][curY] = end;
			}
			if (curY > 0 && via[curX][curY - 1] == 0
					&& (clipMask[curX][curY - 1] & 0x1280102) == 0) {
				bigX[pathLen] = curX;
				bigY[pathLen] = curY - 1;
				via[curX][curY - 1] = 1;
				cost[curX][curY - 1] = end;
				pathLen = (pathLen + 1) % steps;
			}
			if (curY < 103 && via[curX][curY + 1] == 0
					&& (clipMask[curX][curY + 1] & 0x1280120) == 0) {
				bigX[pathLen] = curX;
				bigY[pathLen] = curY + 1;
				pathLen = (pathLen + 1) % steps;
				via[curX][curY + 1] = 4;
				cost[curX][curY + 1] = end;
			}
			if (curX > 0 && curY > 0 && via[curX - 1][curY - 1] == 0
					&& (clipMask[curX - 1][curY - 1] & 0x128010e) == 0
					&& (clipMask[curX - 1][curY] & 0x1280108) == 0
					&& (clipMask[curX][curY - 1] & 0x1280102) == 0) {
				bigX[pathLen] = curX - 1;
				bigY[pathLen] = curY - 1;
				pathLen = (pathLen + 1) % steps;
				via[curX - 1][curY - 1] = 3;
				cost[curX - 1][curY - 1] = end;
			}
			if (curX < 103 && curY > 0 && via[curX + 1][curY - 1] == 0
					&& (clipMask[curX + 1][curY - 1] & 0x1280183) == 0
					&& (clipMask[curX + 1][curY] & 0x1280180) == 0
					&& (clipMask[curX][curY - 1] & 0x1280102) == 0) {
				bigX[pathLen] = curX + 1;
				bigY[pathLen] = curY - 1;
				pathLen = (pathLen + 1) % steps;
				via[curX + 1][curY - 1] = 9;
				cost[curX + 1][curY - 1] = end;
			}
			if (curX > 0 && curY < 103 && via[curX - 1][curY + 1] == 0
					&& (clipMask[curX - 1][curY + 1] & 0x1280138) == 0
					&& (clipMask[curX - 1][curY] & 0x1280108) == 0
					&& (clipMask[curX][curY + 1] & 0x1280120) == 0) {
				bigX[pathLen] = curX - 1;
				bigY[pathLen] = curY + 1;
				pathLen = (pathLen + 1) % steps;
				via[curX - 1][curY + 1] = 6;
				cost[curX - 1][curY + 1] = end;
			}
			if (curX < 103 && curY < 103 && via[curX + 1][curY + 1] == 0
					&& (clipMask[curX + 1][curY + 1] & 0x12801e0) == 0
					&& (clipMask[curX + 1][curY] & 0x1280180) == 0
					&& (clipMask[curX][curY + 1] & 0x1280120) == 0) {
				bigX[pathLen] = curX + 1;
				bigY[pathLen] = curY + 1;
				via[curX + 1][curY + 1] = 12;
				cost[curX + 1][curY + 1] = end;
				pathLen = (pathLen + 1) % steps;
			}
		}
		pathFound = 0;
		if (!foundPath) {
			if (!moveNear)
				return false;
			int fullCost = 1000;
			int deapth = 10;
			int thisCost = 100;
			for (int x = destX - deapth; deapth + destX >= x; x++) {
				for (int y = destY - deapth; deapth + destY >= y; y++) {
					if (x >= 0 && y >= 0 && x < 104 && y < 104
							&& (cost[x][y] < 100)) {
						int diffY = 0;
						if (destY <= y) {
							if (y > ySize + (destY - 1))
								diffY = y + 1 + (-destY - ySize);
						} else
							diffY = destY - y;
						int diffX = 0;
						if (destX <= x) {
							if (x > destX + xSize - 1)
								diffX = x + 1 - destX - xSize;
						} else
							diffX = destX - x;
						int totalCost = diffX * diffX + diffY * diffY;
						if (fullCost > totalCost || totalCost == fullCost
								&& thisCost > (cost[x][y])) {
							thisCost = (cost[x][y]);
							curX = x;
							curY = y;
							fullCost = totalCost;
						}
					}
				}
			}
			if (fullCost == 1000)
				return false;
			if (curX == posX && curY == posY)
				return false;
			pathFound = 1;
		}
		tail = 0;
		bigX[tail] = curX;
		bigY[tail++] = curY;
		int i_35_;
		int i_34_ = i_35_ = via[curX][curY];
		while (curX != posX || posY != curY) {
			if (i_35_ != i_34_) {
				i_35_ = i_34_;
				bigX[tail] = curX;
				bigY[tail++] = curY;
			}
			if ((i_34_ & 0x2) == 0) {
				if ((i_34_ & 0x8) != 0)
					curX--;
			} else
				curX++;
			if ((i_34_ & 0x1) == 0) {
				if ((i_34_ & 0x4) != 0)
					curY--;
			} else
				curY++;
			i_34_ = via[curX][curY];
		}
		if (tail > 0) {
			steps = tail;
			tail--;
			int x = bigX[tail];
			int y = bigY[tail];
			if (steps > 25)
				steps = 25;
			flagX = bigX[0];
			flagY = bigY[0];
			for (int id = 1; steps > id; id++) {
				tail--;
			}
			return true;
		}
		if (walkType == 1)
			return false;
		return true;
	}

	static final byte[] unpack(byte[] buffer) {
		RSByteBuffer stream = new RSByteBuffer(buffer);
		int compression = stream.getUByte();
		int fileSize = stream.getInt();
		if (fileSize >= 0 && Client.anInt262 != 0) {
			if (Client.anInt262 != 0) {
				throw new RuntimeException();
			}
		}
		if (compression == 0) {
			byte[] is_4_ = new byte[fileSize];
			stream.getBytes(is_4_, 0, fileSize);
			return is_4_;
		}
		int i_5_ = stream.getInt();
		if (i_5_ < 0 || i_5_ >= 200000000 || Client.anInt262 != 0
				&& Client.anInt262 < i_5_)
			return new byte[100];
		byte[] decompressedSize = new byte[i_5_];
		if (compression != 1) {
			try {
				DataInputStream datainputstream = (new DataInputStream(
						new GZIPInputStream(new ByteArrayInputStream(buffer, 9,
								fileSize))));
				datainputstream.readFully(decompressedSize);
				datainputstream.close();
			} catch (IOException ioexception) {
				/* empty */
			}
		} else
			BZip2Decompressor.decompress(decompressedSize, i_5_, buffer,
					fileSize, 9);
		return decompressedSize;
	}

	static final int getRotatedMapChunkY(int x, int y, int rotation) {
		rotation &= 0x3;
		if (rotation == 0)
			return y;
		if (rotation == 1)
			return 7 - x;
		if (rotation == 2)
			return 7 - y;
		return x;
	}

	public static final void drawLandscapeOnMinimap(int z) {
		int[] pixels = landScapeAsSprite.pixels;
		int size = pixels.length;
		for (int id = 0; size > id; id++)
			pixels[id] = -16777216;
		for (int y = 0; y < 104; y++) {
			int offset = (52736 - (y * 512)) * 4 + 24628;
			for (int x = 0; x < 104; x++) {
				if (((Client.tileSettings[z][x][y]) & 0x18) == 0)
					Client.sceneGraph
							.drawMinimapPixels(pixels, offset, z, x, y);
				if (z < 3 && ((Client.tileSettings[z + 1][x][y]) & 0x8) != 0)
					Client.sceneGraph.drawMinimapPixels(pixels, offset, z + 1,
							x, y);
				offset += 4;
			}
		}
		int whiteColor = ~0xffffff | 0xFFFFFF;
		int redColor = ~0xffffff | 0xFF0000;
		landScapeAsSprite.init();
		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				if (((Client.tileSettings[z][y][x]) & 0x18) == 0)
					drawMinimapObjects(y, z, x, whiteColor, redColor);
				if (z < 3 && ((Client.tileSettings[z + 1][y][x]) & 0x8) != 0)
					drawMinimapObjects(y, z + 1, x, whiteColor, redColor);
			}
		}
		mapFunctionsCount = 0;
		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				int hash = Client.sceneGraph.getGroundDecorationHash(x, y,
						height);
				if (hash != 0) {
					hash = hash >> 14 & 0x7fff;
					int sprite = ObjectDefinition.forID(hash).mapSceneSprite;
					if (sprite >= 0) {
						mapFunctionsSprite[mapFunctionsCount] = mapFunction[sprite];
						mapFunctionsX[mapFunctionsCount] = x;
						mapFunctionsY[mapFunctionsCount] = y;
						mapFunctionsCount++;
					}
				}
			}
		}
	}

	static final int getRotatedMapChunkX(int x, int y, int rotation) {
		rotation &= 3;
		if (rotation == 0)
			return x;
		if (rotation == 1)
			return y;
		if (rotation == 2)
			return 7 - x;
		return 7 - y;
	}

	public static final GraphicsBuffer constructGraphicsBuffer(Component component,
			int x, int y) {
		GraphicsBuffer graphicClass;
		try {
			Class var_class = Class.forName("BasicGraphicsBuffer");
			GraphicsBuffer instance = (GraphicsBuffer) var_class.newInstance();
			instance.init(component, x, y, false);
			graphicClass = instance;
		} catch (Throwable throwable) {
			ProducingGraphicsBuffer graphicInstance = new ProducingGraphicsBuffer();
			graphicInstance.init(component, x, y, false);
			return graphicInstance;
		}
		return graphicClass;
	}

	public static final int blendUnderlayColor(int i, int i_1_) {
		if(!Editor.disableTileShadow) {
			if (i_1_ == -2)
				return 12345678;
			if (i_1_ == -1) {
				if (i < 0)
					i = 0;
				else if (i > 127)
					i = 127;
				i = 127 - i;
				return i;
			}
			i = i * (i_1_ & 0x7f) / 128;
			if (i < 2)
				i = 2;
			else if (i > 126)
				i = 126;
			return (i_1_ & 0xff80) + i;
		} else {
			if (i_1_ == -2)
				return 12345678;
			if (i_1_ == -1) {
				if (i < 0)
					i = 0;
				else if (i > 127)
					i = 127;
				i = 127 - i;
				return i;
			}
			return i_1_;
		}
	}

	static final void resetSprites() {
		spritePallete = null;
		spriteOffsetX = null;
		spriteWidth = null;
		spritePixels = null;
		spriteOffsetY = null;
		spriteHeight = null;
	}

	static final void method46(RSSocket class16, boolean bool) {
		if (Client.updateServerConnection != null) {
			try {
				Client.updateServerConnection.close();
			} catch (Exception exception) {
				/* empty */
			}
			Client.updateServerConnection = null;
		}
		Client.updateServerConnection = class16;
		resetUpdateServerRequests(bool);
		Client.aClass3_Sub12_2087.index = 0;
		Client.aClass3_Sub3_Sub13_128 = null;
		Client.anInt1744 = 0;
		aClass3_Sub12_1448 = null;
		for (;;) {
			BufferedRequest class3_sub3_sub13 = ((BufferedRequest) Client.aClass40_1630
					.getFirst());
			if (class3_sub3_sub13 == null)
				break;
			aClass40_1146.put(class3_sub3_sub13,
					(((Node) class3_sub3_sub13).hash));
			anInt1501++;
			anInt865--;
		}
		for (;;) {
			BufferedRequest class3_sub3_sub13 = ((BufferedRequest) Client.aClass40_1335
					.getFirst());
			if (class3_sub3_sub13 == null)
				break;
			aClass30_2033.method671(class3_sub3_sub13);
			aClass40_14.put(class3_sub3_sub13,
					(((Node) class3_sub3_sub13).hash));
			anInt1132++;
			anInt335--;
		}
		if (Client.encryption != 0) {
			try {
				RSByteBuffer class3_sub12 = new RSByteBuffer(4);
				class3_sub12.putByte(4);
				class3_sub12.putByte(Client.encryption);
				class3_sub12.putShort(0);
				Client.updateServerConnection.write(class3_sub12.buf, 0, 4);
			} catch (IOException ioexception) {
				try {
					Client.updateServerConnection.close();
				} catch (Exception exception) {
					/* empty */
				}
				Client.updateServerConnection = null;
				Client.failAttemps++;
			}
		}
		Client.latency = 0;
		lastConnection = System.currentTimeMillis();
	}

	static final RSString getStringValue(int i_28_) {
		if (i_28_ < 999999999)
			return RSString.valueOf(i_28_);
		return Client.star;
	}

	static final void drawGameScreen() {
		if(locationToggle) {
			if (!Client.menuOpen) {
				Client.processMouse();
			} else if (menuType == 0)
				Client.drawMenu();
		}
		if (showFps) {
			int y = 20;
			int x = clientFrame.getWidth() - 10;
			int color = 16776960;
			if (fps < 30 && Client.lowMem)
				color = 16711680;
			if (fps < 20 && !Client.lowMem)
				color = 16711680;
			regularFont.drawBasicString(linkRSStrings(new RSString[] {
					fpsString, RSString.valueOf(fps) }), x, y, color);
			y += 15;
			regularFont.drawBasicString(linkRSStrings(new RSString[] {
					RSString.createRSString("Tile: "), RSString.valueOf(SceneGraph.tileMouseOverX), RSString.createRSString(","), RSString.valueOf(SceneGraph.tileMouseOverY) }), x, y, color);
			y += 20;
			regularFont.drawBasicString(linkRSStrings(new RSString[] {
					RSString.createRSString("ID:   "), RSString.valueOf(Editor.getObjectId(SceneGraph.tileMouseOverX, SceneGraph.tileMouseOverY, height)) }), x, y, color);
		}
	}

	static final void resetOverlays() {
		overlayCache.clear();
	}

	static final void processStillGraphics() {
		for (StillGraphic sgfx = (StillGraphic) Client.stillGraphicList
				.getFront(); sgfx != null; sgfx = (StillGraphic) Client.stillGraphicList
				.getNext()) {
			if (height != sgfx.z || sgfx.aBoolean2042)
				sgfx.unlink();
			else if (Client.loopCycle >= sgfx.cycle) {
				sgfx.method157(Client.cycleRate);
				if (!sgfx.aBoolean2042)
					Client.sceneGraph.render(sgfx.z, sgfx.x, sgfx.y,
							sgfx.height, 60, sgfx, 0, -1, false);
				else
					sgfx.unlink();
			}
		}
	}

	static final void drawMinimapObjects(int x, int z, int y, int white, int red) {
		int hash = sceneGraph.getWallObjectHash(x, y, z);
		if (hash != 0) {
			int i_6_ = sceneGraph.getUID(z, x, y, hash);
			int direction = i_6_ >> 6 & 0x3;
			int type = i_6_ & 0x1f;
			int color = white;
			if (hash > 0)
				color = red;
			int alpha = color >>> 24;
			color = (((color & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((color & 0xff00) * alpha >> 8 & 0xff00) + (alpha << 24));
			int[] pixels = landScapeAsSprite.pixels;
			int i_11_ = 24624 + x * 4 + (103 - y) * 512 * 4;
			int objectId = hash >> 14 & 0x7fff;
			ObjectDefinition objectDef = ObjectDefinition.forID(objectId);
			if (objectDef.groundDecorationSprite != -1) {
				IndexedSprite sprite = mapScene[objectDef.groundDecorationSprite];
				if (sprite != null) {
					int spriteY = (objectDef.sizeY * 4 - sprite.height) / 2;
					int spriteX = (objectDef.sizeX * 4 - sprite.width) / 2;
					sprite.drawIndexedSprite(48 + x * 4 + spriteX, 48
							+ (104 - y - objectDef.sizeY) * 4 + spriteY);
				}
			} else {
				if (type == 0 || type == 2) {
					if (direction == 0) {
						pixels[i_11_] = color;
						pixels[i_11_ + 512] = color;
						pixels[i_11_ + 1024] = color;
						pixels[i_11_ + 1536] = color;
					} else if (direction == 1) {
						pixels[i_11_] = color;
						pixels[i_11_ + 1] = color;
						pixels[i_11_ + 2] = color;
						pixels[i_11_ + 3] = color;
					} else if (direction == 2) {
						pixels[i_11_ + 3] = color;
						pixels[i_11_ + 512 + 3] = color;
						pixels[i_11_ + 1024 + 3] = color;
						pixels[i_11_ + 1536 + 3] = color;
					} else if (direction == 3) {
						pixels[i_11_ + 1536] = color;
						pixels[i_11_ + 1536 + 1] = color;
						pixels[i_11_ + 1536 + 2] = color;
						pixels[i_11_ + 1536 + 3] = color;
					}
				}
				if (type == 3) {
					if (direction == 0)
						pixels[i_11_] = color;
					else if (direction == 1)
						pixels[i_11_ + 3] = color;
					else if (direction == 2)
						pixels[i_11_ + 1536 + 3] = color;
					else if (direction == 3)
						pixels[i_11_ + 1536] = color;
				}
				if (type == 2) {
					if (direction == 3) {
						pixels[i_11_] = color;
						pixels[i_11_ + 512] = color;
						pixels[i_11_ + 1024] = color;
						pixels[i_11_ + 1536] = color;
					} else if (direction == 0) {
						pixels[i_11_] = color;
						pixels[i_11_ + 1] = color;
						pixels[i_11_ + 2] = color;
						pixels[i_11_ + 3] = color;
					} else if (direction == 1) {
						pixels[i_11_ + 3] = color;
						pixels[i_11_ + 3 + 512] = color;
						pixels[i_11_ + 3 + 1024] = color;
						pixels[i_11_ + 3 + 1536] = color;
					} else if (direction == 2) {
						pixels[i_11_ + 1536] = color;
						pixels[i_11_ + 1536 + 1] = color;
						pixels[i_11_ + 1536 + 2] = color;
						pixels[i_11_ + 1536 + 3] = color;
					}
				}
			}
		}
		hash = sceneGraph.getInteractiveObjectHash(x, y, z);
		if (hash != 0) {
			int i_14_ = sceneGraph.getUID(z, x, y, hash);
			int direction = (i_14_ & 0xdb) >> 6;
			int type = i_14_ & 0x1f;
			int objectId = (hash & 0x1fffe1ee) >> 14;
			ObjectDefinition objectDef = ObjectDefinition.forID(objectId);
			if (objectDef.groundDecorationSprite != -1) {
				IndexedSprite sprite = mapScene[objectDef.groundDecorationSprite];
				if (sprite != null) {
					int spriteX = (objectDef.sizeX * 4 - sprite.width) / 2;
					int spriteY = (objectDef.sizeY * 4 - sprite.height) / 2;
					sprite.drawIndexedSprite(48 + x * 4 + spriteX,
							(104 - y - objectDef.sizeY) * 4 + 48 + spriteY);
				}
			} else if (type == 9) {
				int color = 15658734;
				int i_21_ = x * 4 + 24624 + (103 - y) * 2048;
				if (hash > 0)
					color = 15597568;
				int[] pixels = landScapeAsSprite.pixels;
				if (direction == 0 || direction == 2) {
					pixels[i_21_ + 1536] = color;
					pixels[i_21_ + 1024 + 1] = color;
					pixels[i_21_ + 512 + 2] = color;
					pixels[i_21_ + 3] = color;
				} else {
					pixels[i_21_] = color;
					pixels[i_21_ + 512 + 1] = color;
					pixels[i_21_ + 1024 + 2] = color;
					pixels[i_21_ + 1536 + 3] = color;
				}
			}
		}
		hash = sceneGraph.getGroundDecorationHash(x, y, z);
		if (hash != 0) {
			int id = hash >> 14 & 0x7fff;
			ObjectDefinition objectDef = ObjectDefinition.forID(id);
			if (objectDef.groundDecorationSprite != -1) {
				IndexedSprite sprite = mapScene[objectDef.groundDecorationSprite];
				if (sprite != null) {
					int posX = (objectDef.sizeX * 4 - sprite.width) / 2;
					int posY = (objectDef.sizeY * 4 - sprite.height) / 2;
					sprite.drawIndexedSprite(x * 4 + 48 + posX, posY
							+ ((104 - y - objectDef.sizeY) * 4 + 48));
				}
			}
		}
	}

	static final void resetSequence() {
		sequenceCache.clear();
		animationCache.clear();
	}

	static final void markMinimap(Sprite sprite, int x, int y) {
		if (sprite != null) {
			int yaw = Client.cameraYaw & 0x7ff;
			int distance = x * x + y * y;
			if (distance <= 6400) {
				int sineYaw = Model.sine[yaw];
				int cosineYaw = Model.cosine[yaw];
				int posY = y * cosineYaw - x * sineYaw >> 16;
				int posX = x * cosineYaw + y * sineYaw >> 16;
				if (distance > 2500)
					sprite.drawIndexedSprite(mapBack, -((sprite.trimWidth) / 2)
							+ posX + 94 + 4, -((sprite.trimHeight) / 2) - 4
							+ (-posY + 83));
				else
					sprite.drawSprite(98 - (sprite.trimWidth / 2 - posX), -posY
							- 4 + (-(sprite.trimHeight / 2) + 83));
			}
		}
	}

	static final void processLoadState() {
		if (Client.lowMem && height != oldHeight)
			Client.updateRegion(height, Client.oldRegionX, Client.oldRegionY);
		else if (height != minimapHeight) {
			minimapHeight = height;
			//Client.drawLandscapeOnMinimap(height);
		}
	}

	static final int method826(boolean bool, boolean bool_1_) {
		int i = 0;
		if (bool_1_)
			i += anInt865 + anInt1501;
		if (bool)
			i += anInt1132 + Client.anInt335;
		return i;
	}

	static final void performAction(int opcode, RSString class63, int i,
			int i_3_, int i_5_) {
		if (Client.menuActionCount < 500) {
			menuActionName[Client.menuActionCount] = class63;
			Client.menuActionOpcode[Client.menuActionCount] = opcode;
			Client.menuActionIndex[Client.menuActionCount] = i_5_;
			Client.menuActionCmd3[Client.menuActionCount] = i_3_;
			Client.menuActionCmd2[Client.menuActionCount] = i;
			Client.menuActionCount++;
		}
	}

	static final void resetVarp() {
		Client.varpCache.clear();
	}

	static final void drawLoadingText(Color color, RSString text, int percent) {
		try {
			Graphics graphics = Client.canvas.getGraphics();
			if (Client.loadingFont == null) {
				Client.loadingFont = new Font("Helvetica", 1, 13);
				Client.loadingFontMetrics = Client.canvas
						.getFontMetrics(Client.loadingFont);
			}
			if (Client.redrawGameframe) {
				Client.redrawGameframe = false;
				graphics.setColor(Color.black);
				graphics.fillRect(0, 0, Client.sizeX, sizeY);
			}
			if (color == null)
				color = new Color(140, 17, 17);
			try {
				if (loadingTextImage == null)
					loadingTextImage = Client.canvas.createImage(304, 34);
				Graphics imageGraphics = loadingTextImage.getGraphics();
				imageGraphics.setColor(color);
				imageGraphics.drawRect(0, 0, 303, 33);
				imageGraphics.fillRect(2, 2, percent * 3, 30);
				imageGraphics.setColor(Color.black);
				imageGraphics.drawRect(1, 1, 301, 31);
				imageGraphics.fillRect(percent * 3 + 2, 2, 300 - (percent * 3),
						30);
				imageGraphics.setFont(Client.loadingFont);
				imageGraphics.setColor(Color.white);
				text.drawString(imageGraphics,
						(304 - text.getWidth(Client.loadingFontMetrics)) / 2,
						22);
				graphics.drawImage(loadingTextImage, Client.sizeX / 2 - 152,
						sizeY / 2 - 18, null);
			} catch (Exception exception) {
				int x = Client.sizeX / 2 - 152;
				int y = sizeY / 2 - 18;
				graphics.setColor(color);
				graphics.drawRect(x, y, 303, 33);
				graphics.fillRect(x + 2, y + 2, percent * 3, 30);
				graphics.setColor(Color.black);
				graphics.drawRect(x + 1, y + 1, 301, 31);
				graphics.fillRect(percent * 3 + (x + 2), y + 2,
						300 - (percent * 3), 30);
				graphics.setFont(Client.loadingFont);
				graphics.setColor(Color.white);
				text.drawString(graphics,
						x + (304 - text.getWidth(Client.loadingFontMetrics))
								/ 2, y + 22);
			}
		} catch (Exception exception) {
			Client.canvas.repaint();
		}
	}

	static final Class getClassType(String string)
			throws ClassNotFoundException {
		if (string.equals("B"))
			return Byte.TYPE;
		if (string.equals("I"))
			return Integer.TYPE;
		if (string.equals("S"))
			return Short.TYPE;
		if (string.equals("J"))
			return Long.TYPE;
		if (string.equals("Z"))
			return Boolean.TYPE;
		if (string.equals("F"))
			return Float.TYPE;
		if (string.equals("D"))
			return Double.TYPE;
		if (string.equals("C"))
			return Character.TYPE;
		return Class.forName(string);
	}

	static final int method74(int i, int i_13_, int i_14_) {
		int i_16_ = i_14_ / i_13_;
		int i_17_ = i / i_13_;
		int i_18_ = i_13_ - 1 & i;
		int i_19_ = i_14_ & i_13_ - 1;
		int i_20_ = Client.method505(i_17_, i_16_);
		int i_21_ = Client.method505(i_17_ + 1, i_16_);
		int i_22_ = Client.method505(i_17_, i_16_ + 1);
		int i_23_ = Client.method505(i_17_ + 1, i_16_ + 1);
		int i_24_ = method656(i_13_, i_20_, i_18_, i_21_);
		int i_25_ = method656(i_13_, i_22_, i_18_, i_23_);
		return method656(i_13_, i_24_, i_19_, i_25_);
	}

	static final void resetLoadingBar() {
		loadingFont = null;
		loadingFontMetrics = null;
		loadingTextImage = null;
	}

	static final void method75(int i, CacheFileWorker class18_sub1,
			CacheFile class28) {
		/*
		 * byte[] is = null; synchronized (Client.updateServerList) { for
		 * (UpdateServerNode class3_sub10 = (UpdateServerNode)
		 * Client.updateServerList .getFront(); class3_sub10 != null;
		 * class3_sub10 = (UpdateServerNode) Client.updateServerList .getNext())
		 * { if ((long) i == class3_sub10.hash && class28 == class3_sub10.cache
		 * && class3_sub10.anInt1246 == 0) { is = class3_sub10.data; break; } }
		 * } if (is != null) class18_sub1.method596(is, true, i, class28); else
		 * {
		 */
		byte[] is_28_ = class28.get(i);
		class18_sub1.method596(is_28_, true, i, class28);
		// }
	}

	static final void drawMinimap() {
		initMinimapBuffer();
		Graphics2D.resetpixels();
		
		if(landScapeAsSprite != null)
			landScapeAsSprite.drawSprite(0, -150);
		if(compass != null) {
			compass.drawShapedSprite(0, 0, 33, 33, 25, 25, Client.cameraYaw & 0x7ff, 256, Client.compassSource, compassDestination);
		}
		///for (int id = 0; id < mapFunctionsCount; id++) {
		//	y = mapFunctionsY[id] * 4 + 2 - (Client.playerRenderY / 32);
		//	x = mapFunctionsX[id] * 4 + 2 - (Client.playerRenderX / 32);
		//	markMinimap(Client.mapFunctionsSprite[id], x, y);
		//}
		/*for (int id = 0; id < localPlayerCount; id++) {
			Player player = Client.localPlayers[Client.playerIndices[id]];
			if (player != null && player.isVisible()) {
				x = player.x / 32 - playerRenderX / 32;
				y = player.y / 32 - playerRenderY / 32;
				boolean isFriend = false;
				long nameAsLong = player.name.toLong();
				for (int id_ = 0; id_ < Client.friendsCount; id_++) {
					if (nameAsLong == friendList[id_]
							&& Client.friendsWorlds[id_] != 0) {
						isFriend = true;
						break;
					}
				}
				boolean isTeam = false;
				if (Client.myPlayer.team != 0 && player.team != 0
						&& Client.myPlayer.team == player.team)
					isTeam = true;
				if (isFriend)
					Client.markMinimap(mapDot[3], x, y);
				else if (isTeam)
					Client.markMinimap(mapDot[4], x, y);
				else
					Client.markMinimap(mapDot[2], x, y);
			}
		}
		if (flagX != 0) {
			x = (flagX * 4 + 2) - (Client.playerRenderX / 32);
			y = (flagY * 4 + 2) - (Client.playerRenderY / 32);
			Client.markMinimap(Client.mapMarker[0], x, y);
		}*/
		//Graphics2D.fillRect(97, 78, 3, 3, 16777215);
		drawMinimapBuffer();
	}

	static final void method162() {
		int x = Client.lookFromX * 128 + 64;
		int y = Client.lookFromY * 128 + 64;
		int z = (getTileHeight(x, y, height) - Client.lookFromHeight);
		if (Client.renderX < x) {
			Client.renderX += (Client.lookFromSpeed + (x - Client.renderX)
					* lookFromAngle / 1000);
			if (Client.renderX > x)
				Client.renderX = x;
		}
		if (y > renderY) {
			renderY += (Client.lookFromSpeed + (y - renderY) * lookFromAngle
					/ 1000);
			if (renderY > y)
				renderY = y;
		}
		if (z > renderZ) {
			renderZ += (Client.lookFromSpeed + lookFromAngle * (z - renderZ)
					/ 1000);
			if (z < renderZ)
				renderZ = z;
		}
		if (z < renderZ) {
			renderZ -= (Client.lookFromSpeed + lookFromAngle * (renderZ - z)
					/ 1000);
			if (renderZ < z)
				renderZ = z;
		}
		if (renderY > y) {
			renderY -= (Client.lookFromSpeed + (renderY - y) * lookFromAngle
					/ 1000);
			if (renderY < y)
				renderY = y;
		}
		if (x < Client.renderX) {
			Client.renderX -= (lookFromAngle * (renderX - x) / 1000 + Client.lookFromSpeed);
			if (Client.renderX < x)
				Client.renderX = x;
		}
		x = Client.lookAtX * 128 + 64;
		y = Client.lookAtY * 128 + 64;
		z = (getTileHeight(x, y, height) - Client.lookAtHeight);
		int xDiff = x - Client.renderX;
		int yDiff = y - renderY;
		int zDiff = z - renderZ;
		int distance = (int) Math
				.sqrt((double) (xDiff * xDiff + yDiff * yDiff));
		int pitch = ((int) (Math.atan2((double) zDiff, (double) distance) * 325.949) & 0x7ff);
		if (pitch < 128)
			pitch = 128;
		if (pitch > 383)
			pitch = 383;
		int yaw = ((int) (Math.atan2((double) xDiff, (double) yDiff) * -325.949) & 0x7ff);
		int yawDiff = yaw - Client.renderYaw;
		if (Client.renderPitch < pitch) {
			Client.renderPitch += lookAtAngle * (pitch - Client.renderPitch)
					/ 1000 + Client.lookAtSpeed;
			if (Client.renderPitch > pitch)
				Client.renderPitch = pitch;
		}
		if (Client.renderPitch > pitch) {
			Client.renderPitch -= Client.lookAtSpeed
					+ (lookAtAngle * (Client.renderPitch - pitch) / 1000);
			if (Client.renderPitch < pitch)
				Client.renderPitch = pitch;
		}
		if (yawDiff > 1024)
			yawDiff -= 2048;
		if (yawDiff < -1024)
			yawDiff += 2048;
		if (yawDiff > 0) {
			Client.renderYaw += lookAtAngle * yawDiff / 1000
					+ Client.lookAtSpeed;
			Client.renderYaw &= 0x7ff;
		}
		if (yawDiff < 0) {
			Client.renderYaw -= Client.lookAtSpeed + lookAtAngle * -yawDiff
					/ 1000;
			Client.renderYaw &= 0x7ff;
		}
		int i_9_ = yaw - Client.renderYaw;
		if (i_9_ > 1024)
			i_9_ -= 2048;
		if (i_9_ < -1024)
			i_9_ += 2048;
		if (i_9_ < 0 && yawDiff > 0 || i_9_ > 0 && yawDiff < 0)
			Client.renderYaw = yaw;
	}

	static final void method163(int i, int i_10_, int i_11_, byte[] is,
			int i_12_, int i_13_, CollisionMap[] class59s, int i_14_,
			SceneGraph class64, int i_15_) {
		int i_16_ = -1;
		RSByteBuffer class3_sub12 = new RSByteBuffer(is);
		for (;;) {
			int i_17_ = class3_sub12.getUSmart();
			if (i_17_ == 0)
				break;
			i_16_ += i_17_;
			int i_18_ = 0;
			for (;;) {
				int i_19_ = class3_sub12.getUSmart();
				if (i_19_ == 0)
					break;
				i_18_ += i_19_ - 1;
				int i_20_ = i_18_ & 0x3f;
				int i_21_ = (i_18_ & 0xfc0) >> 6;
				int i_22_ = i_18_ >> 12;
				int i_23_ = class3_sub12.getUByte();
				int i_24_ = i_23_ >> 2;
				int i_25_ = i_23_ & 0x3;
				if (i_22_ == i && i_14_ <= i_21_ && i_14_ + 8 > i_21_
						&& i_10_ <= i_20_ && i_20_ < i_10_ + 8) {
					ObjectDefinition class3_sub3_sub14 = ObjectDefinition
							.forID(i_16_);
					int i_26_ = i_15_;
					int i_27_ = (Client.getRotatedLandscapeChunkX(
							class3_sub3_sub14.sizeX, i_20_ & 0x7, i_12_, i_25_,
							class3_sub3_sub14.sizeY, i_21_ & 0x7));
					int i_28_ = i_27_ + i_26_;
					int i_29_ = Client.getRotatedLandscapeChunkY(i_12_,
							i_20_ & 0x7, i_25_, i_21_ & 0x7,
							class3_sub3_sub14.sizeY, class3_sub3_sub14.sizeX)
							+ i_13_;
					if (i_28_ > 0 && i_29_ > 0 && i_28_ < 103 && i_29_ < 103) {
						int i_30_ = i_11_;
						CollisionMap class59 = null;
						if (((Client.tileSettings[1][i_28_][i_29_]) & 0x2) == 2)
							i_30_--;
						if (i_30_ >= 0)
							class59 = class59s[i_30_];
						Client.method1006(i_28_, class59, i_25_ + i_12_ & 0x3,
								i_16_, i_11_, class64, i_24_, i_29_);
					}
				}
			}
		}
	}

	static final void method164(boolean bool, CacheFileWorker class18_sub1,
			int crc, byte i_32_, int i_33_, int i_34_) {
		long l = (long) ((i_33_ << 16) + i_34_);
		BufferedRequest class3_sub3_sub13 = (BufferedRequest) aClass40_1146
				.get(l);
		if (class3_sub3_sub13 == null) {
			class3_sub3_sub13 = (BufferedRequest) Client.aClass40_1630.get(l);
			if (class3_sub3_sub13 == null) {
				class3_sub3_sub13 = (BufferedRequest) Client.aClass40_14.get(l);
				if (class3_sub3_sub13 != null) {
					if (bool) {
						class3_sub3_sub13.unlinkSub();
						aClass40_1146.put(class3_sub3_sub13, l);
						anInt1132--;
						anInt1501++;
					}
				} else {
					if (!bool) {
						class3_sub3_sub13 = (BufferedRequest) Client.aClass40_1335
								.get(l);
						if (class3_sub3_sub13 != null)
							return;
					}
					class3_sub3_sub13 = new BufferedRequest();
					class3_sub3_sub13.blockPosition = crc;
					class3_sub3_sub13.worker = class18_sub1;
					class3_sub3_sub13.padding = i_32_;
					if (!bool) {
						aClass30_2033.insertBack(class3_sub3_sub13);
						Client.aClass40_14.put(class3_sub3_sub13, l);
						anInt1132++;
					} else {
						aClass40_1146.put(class3_sub3_sub13, l);
						anInt1501++;
					}
				}
			}
		}
	}

	static final boolean method165(byte[] is, int i_35_, int i_36_) {
		boolean bool = true;
		try {
			i_35_ = 0;
			i_36_ = 0;
			int objectId = -1;
			RSByteBuffer class3_sub12 = new RSByteBuffer(is);
			for (; ; ) {
				int i_39_ = class3_sub12.getUSmart();
				if (i_39_ == 0)
					break;
				int i_40_ = 0;
				objectId += i_39_;
				boolean bool_41_ = false;
				for (; ; ) {
					if (!bool_41_) {
						int i_42_ = class3_sub12.getUSmart();
						if (i_42_ == 0)
							break;
						i_40_ += i_42_ - 1;
						int i_43_ = i_40_ & 0x3f;
						int type = class3_sub12.getUByte() >> 2;
						int i_45_ = (i_40_ & 0xfcb) >> 6;
						int x = i_36_ + i_45_;
						int y = i_43_ + i_35_;
						if (x > 0 && y > 0 && x < 103 && y < 103) {
							ObjectDefinition objectDef = ObjectDefinition
									.forID(objectId);
							if (type != 22 || !Client.lowMem
									|| objectDef.hasActions != 0
									|| objectDef.aBoolean1665) {
								bool_41_ = true;
								if (!objectDef.modelCached()) {
									Client.modelFetchCount++;
									bool = false;
								}
							}
						}
					} else {
						int i_48_ = class3_sub12.getUSmart();
						if (i_48_ == 0)
							break;
						class3_sub12.getUByte();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("leanbow sucks bad");
		}
		return bool;
	}

	static final boolean isNumber(int i_49_) {
		if (i_49_ < 48 || i_49_ > 57)
			return false;
		return true;
	}

	static final void method689(int i) {
		Client.anInt1408 += i;
		while (Client.anInt1408 >= Client.anInt197) {
			Client.anInt1408 -= Client.anInt197;
			anInt1526 -= anInt1526 >> 2;
		}
		anInt1526 -= i * 1000;
		if (anInt1526 < 0)
			anInt1526 = 0;
	}

	static final void drawMinimapBuffer() {
		try {
			Graphics graphics = GUI.minimapPanel.getGraphics();
			Client.minimapBuffer.drawGraphics(graphics, 0, 0);
		} catch (Exception exception) {
			Client.canvas.repaint();
		}
	}

	static final void method692(int i_1_, int i_2_, CollisionMap[] class59s,
			int i_3_, int i_4_, int i_5_, int i_6_, int i_7_, byte[] is) {
		for (int i_8_ = 0; i_8_ < 8; i_8_++) {
			for (int i_9_ = 0; i_9_ < 8; i_9_++) {
				if (i_6_ + i_8_ > 0 && i_6_ + i_8_ < 103 && i_9_ + i_7_ > 0
						&& i_7_ + i_9_ < 103)
					class59s[i_1_].collisionFlags[i_8_ + i_6_][i_9_ + i_7_] &= -16777217;
			}
		}
		RSByteBuffer class3_sub12 = new RSByteBuffer(is);
		for (int i_10_ = 0; i_10_ < 4; i_10_++) {
			for (int i_11_ = 0; i_11_ < 64; i_11_++) {
				for (int i_12_ = 0; i_12_ < 64; i_12_++) {
					if (i_5_ != i_10_ || i_2_ > i_11_ || i_2_ + 8 <= i_11_
							|| i_12_ < i_4_ || i_12_ >= i_4_ + 8)
						Client.method750(0, -1, 0, 0, 0, -1, class3_sub12);
					else {
						int i_13_ = 0;
						int i_14_ = i_7_
								+ Client.getRotatedMapChunkY(i_11_ & 0x7,
										i_12_ & 0x7, i_3_);
						int i_15_ = i_3_;
						int i_16_ = i_1_;
						int i_17_ = 0;
						int i_19_ = i_6_;
						int i_20_ = Client.getRotatedMapChunkX(i_11_ & 0x7,
								i_12_ & 0x7, i_3_);
						Client.method750(i_13_, i_14_, i_15_, i_16_, i_17_,
								i_20_ + i_19_, class3_sub12);
					}
				}
			}
		}
	}

	static final void method682(CacheFileWorker class18_sub1, int i_0_) {
		if (Client.aClass3_Sub12_1667 != null) {
			Client.aClass3_Sub12_1667.index = i_0_ * 4 + 5;
			int i_1_ = Client.aClass3_Sub12_1667.getInt();
			class18_sub1.setIndexCrc(i_1_);
		} else {
			Client.method164(true, null, 0, (byte) 0, 255, 255);
			aClass18_Sub1Array745[i_0_] = class18_sub1;
		}
	}

	static final void resetAppearance() {
		Client.playerAppearanceCache.clear();
	}

	static final void method686(int i, CollisionMap[] class59s, byte[] is,
			int i_5_, int i_6_, int i_7_) {
		i_5_ = 0;
		i_7_ = 0;
		for (int i_8_ = 0; i_8_ < 4; i_8_++) {
			for (int i_9_ = 0; i_9_ < 64; i_9_++) {
				for (int i_10_ = 0; i_10_ < 64; i_10_++) {
					if (i_9_ + i_7_ > 0 && i_7_ + i_9_ < 103
							&& i_5_ + i_10_ > 0 && i_5_ + i_10_ < 103)
						class59s[i_8_].collisionFlags[i_7_ + i_9_][i_5_ + i_10_] &= -16777217;
				}
			}
		}
		RSByteBuffer class3_sub12 = new RSByteBuffer(is);
		for (int i_11_ = 0; i_11_ < 4; i_11_++) {
			for (int i_12_ = 0; i_12_ < 64; i_12_++) {
				for (int i_13_ = 0; i_13_ < 64; i_13_++)
					Client.method750(i, i_13_ + i_5_, 0, i_11_, i_6_, i_7_
							+ i_12_, class3_sub12);
			}
		}
	}

	static final void resetUpdateServerRequests(boolean bool) {
		if (Client.updateServerConnection != null) {
			try {
				RSByteBuffer class3_sub12 = new RSByteBuffer(4);
				class3_sub12.putByte(!bool ? 3 : 2);
				class3_sub12.putTriByte(0);
				Client.updateServerConnection.write(class3_sub12.buf, 0, 4);
			} catch (IOException ioexception) {
				try {
					Client.updateServerConnection.close();
				} catch (Exception exception) {
					/* empty */
				}
				Client.failAttemps++;
				Client.updateServerConnection = null;
			}
		}
	}

	static final boolean processUpdateServer() {
		do {
			long time = System.currentTimeMillis();
			int timeDiff = (int) (time - lastConnection);
			lastConnection = time;
			if (timeDiff > 200)
				timeDiff = 200;
			latency += timeDiff;
			if (anInt335 == 0 && anInt865 == 0 && anInt1132 == 0
					&& anInt1501 == 0)
				return true;
			if (updateServerConnection == null)
				return false;
			try {
				if (latency > 30000)
					throw new IOException();
				while (anInt865 < 20) {
					if (anInt1501 <= 0)
						break;
					BufferedRequest request = (BufferedRequest) aClass40_1146
							.getFirst();
					RSByteBuffer buffer = new RSByteBuffer(4);
					buffer.putByte(1);
					buffer.putTriByte((int) request.hash);
					updateServerConnection.write(buffer.buf, 0, 4);
					aClass40_1630.put(request, request.hash);
					anInt865++;
					anInt1501--;
				}
				for (/**/; anInt335 < 20 && anInt1132 > 0; anInt335++) {
					BufferedRequest class3_sub3_sub13 = (BufferedRequest) aClass30_2033
							.method672(0);
					RSByteBuffer class3_sub12 = new RSByteBuffer(4);
					class3_sub12.putByte(0);
					class3_sub12.putTriByte((int) class3_sub3_sub13.hash);
					updateServerConnection.write(class3_sub12.buf, 0, 4);
					class3_sub3_sub13.unlinkSub();
					aClass40_1335
							.put(class3_sub3_sub13, class3_sub3_sub13.hash);
					anInt1132--;
				}
				for (int i_1_ = 0; i_1_ < 100; i_1_++) {
					int avail = updateServerConnection.available();
					if (avail < 0)
						throw new IOException();
					if (avail == 0)
						break;
					int i_3_ = 0;
					if (aClass3_Sub3_Sub13_128 != null) {
						if (anInt1744 == 0)
							i_3_ = 1;
					} else
						i_3_ = 8;
					latency = 0;
					if (i_3_ > 0) {
						int i_4_ = i_3_ - aClass3_Sub12_2087.index;
						if (avail < i_4_)
							i_4_ = avail;
						updateServerConnection.read(aClass3_Sub12_2087.buf,
								aClass3_Sub12_2087.index, i_4_);
						if (encryption != 0) {
							for (int i_5_ = 0; i_4_ > i_5_; i_5_++)
								aClass3_Sub12_2087.buf[Client.aClass3_Sub12_2087.index
										+ i_5_] ^= encryption;
						}
						aClass3_Sub12_2087.index += i_4_;
						if (aClass3_Sub12_2087.index < i_3_)
							break;
						if (aClass3_Sub3_Sub13_128 == null) {
							aClass3_Sub12_2087.index = 0;
							int cache = Client.aClass3_Sub12_2087.getUByte();
							int file = Client.aClass3_Sub12_2087.getUShort();
							long uid = (long) (file + (cache << 16));
							int settings = Client.aClass3_Sub12_2087.getUByte();
							int fileSize = Client.aClass3_Sub12_2087.getInt();
							BufferedRequest class3_sub3_sub13 = (BufferedRequest) aClass40_1630
									.get(uid);
							aBoolean159 = true;
							if (class3_sub3_sub13 == null) {
								class3_sub3_sub13 = (BufferedRequest) aClass40_1335
										.get(uid);
								aBoolean159 = false;
							}
							if (class3_sub3_sub13 == null)
								throw new IOException();
							aClass3_Sub3_Sub13_128 = class3_sub3_sub13;
							int i_11_ = settings == 0 ? 5 : 9;
							aClass3_Sub12_1448 = new RSByteBuffer(fileSize + i_11_
									+ aClass3_Sub3_Sub13_128.padding);
							aClass3_Sub12_1448.putByte(settings);
							aClass3_Sub12_1448.putInt(fileSize);
							anInt1744 = 8;
							aClass3_Sub12_2087.index = 0;
						} else if (anInt1744 == 0) {
							if (aClass3_Sub12_2087.buf[0] == -1) {
								aClass3_Sub12_2087.index = 0;
								anInt1744 = 1;
							} else
								aClass3_Sub3_Sub13_128 = null;
						}
					} else {
						int i_12_ = aClass3_Sub12_1448.buf.length
								- aClass3_Sub3_Sub13_128.padding;
						int i_13_ = 512 - anInt1744;
						if (i_12_ - aClass3_Sub12_1448.index < i_13_)
							i_13_ = i_12_ - aClass3_Sub12_1448.index;
						if (avail < i_13_)
							i_13_ = avail;
						updateServerConnection.read(aClass3_Sub12_1448.buf,
								aClass3_Sub12_1448.index, i_13_);
						if (encryption != 0) {
							for (int i_14_ = 0; i_14_ < i_13_; i_14_++)
								aClass3_Sub12_1448.buf[aClass3_Sub12_1448.index
										+ i_14_] ^= encryption;
						}
						anInt1744 += i_13_;
						aClass3_Sub12_1448.index += i_13_;
						if (i_12_ == aClass3_Sub12_1448.index) {
							if (aClass3_Sub3_Sub13_128.hash == 16711935L) {
								aClass3_Sub12_1667 = aClass3_Sub12_1448;
								for (int i_15_ = 0; i_15_ < 256; i_15_++) {
									CacheFileWorker class18_sub1 = aClass18_Sub1Array745[i_15_];
									if (class18_sub1 != null) {
										Client.aClass3_Sub12_1667.index = i_15_ * 4 + 5;
										int i_16_ = 0;// Client.aClass3_Sub12_1667
										// .getInt();
										class18_sub1.setIndexCrc(i_16_);
									}
								}
							} else {
								aCRC32_2190.reset();
								aCRC32_2190.update(aClass3_Sub12_1448.buf, 0,
										i_12_);
								int i_17_ = (int) aCRC32_2190.getValue();
								if (i_17_ != aClass3_Sub3_Sub13_128.blockPosition) {
									try {
										updateServerConnection.close();
									} catch (Exception exception) {
									}
									encryption = (byte) (int) (255.0 * Math
											.random() + 1.0);
									updateServerConnection = null;
									anInt1439 = 0;
									return false;
								}
								anInt1439 = 100;
								failAttemps = 0;
								aClass3_Sub3_Sub13_128.worker
										.method602(
												aClass3_Sub12_1448.buf,
												(int) (Client.aClass3_Sub3_Sub13_128.hash & 0xffffL),
												(aClass3_Sub3_Sub13_128.hash & 0xff0000L) == 16711680L,
												aBoolean159);
							}
							aClass3_Sub3_Sub13_128.unlink();
							aClass3_Sub3_Sub13_128 = null;
							anInt1744 = 0;
							if (!aBoolean159)
								anInt335--;
							else
								anInt865--;
							aClass3_Sub12_1448 = null;
						} else {
							if (anInt1744 != 512)
								break;
							anInt1744 = 0;
						}
					}
				}
			} catch (IOException ioexception) {
				try {
					updateServerConnection.close();
				} catch (Exception exception) {
					/* empty */
				}
				updateServerConnection = null;
				failAttemps++;
				break;
			}
			return true;
		} while (false);
		return false;
	}

	static final void processCustomObjects() {
		for (CustomObjectSpawn customObject = (CustomObjectSpawn) Client.customObjectSpawns
				.getFront(); customObject != null; customObject = (CustomObjectSpawn) Client.customObjectSpawns
				.getNext()) {
			if (customObject.time > 0)
				customObject.time--;
			if (customObject.time != 0) {
				if (customObject.anInt1189 > 0)
					customObject.anInt1189--;
				if (customObject.anInt1189 == 0
						&& customObject.x >= 1
						&& customObject.y >= 1
						&& customObject.x <= 102
						&& customObject.y <= 102
						&& (customObject.id < 0 || method645(customObject.id,
								customObject.type))) {
					Client.method483(customObject.id, customObject.x,
							customObject.type, customObject.z,
							customObject.anInt1203,
							customObject.sceneGraphType, customObject.y);
					customObject.anInt1189 = -1;
					if ((customObject.id != customObject.anInt1197)
							|| customObject.anInt1197 != -1) {
						if ((customObject.id == customObject.anInt1197)
								&& (customObject.anInt1196 == customObject.anInt1203)
								&& (customObject.anInt1198 == customObject.type))
							customObject.unlink();
					} else
						customObject.unlink();
				}
			} else if (customObject.anInt1197 < 0
					|| method645(customObject.anInt1197, customObject.anInt1198)) {
				Client.method483(customObject.anInt1197, customObject.x,
						customObject.anInt1198, customObject.z,
						customObject.anInt1196, customObject.sceneGraphType,
						customObject.y);
				customObject.unlink();
			}
		}
	}

	static final boolean spriteExists(FileSystem fetcher, int file, int index) {
		byte[] buffer = fetcher.getXteaBuffer(file, index);
		if (buffer == null)
			return false;
		unpackSprites(buffer);
		return true;
	}

	static final boolean yellowboxActive(int id, int type) {
		if (type == 0 && id == Client.gamescreenYellowBox)
			return true;
		if (type == 1 && id == tabareaYellowbox)
			return true;
		if ((type == 2 || type == 3) && chatboxYellowbox == id)
			return true;
		return false;
	}

	static final int getTileHeight(int x, int y, int height) {
		int posX = x >> 7;
		int posY = y >> 7;
		if (posX < 0 || posY < 0 || posX > 103 || posY > 103)
			return 0;
		int transformedHeight = height;
		if (transformedHeight < 3
				&& ((Client.tileSettings[1][posX][posY]) & 0x2) == 2)
			transformedHeight++;
		int i_116_ = x & 0x7f;
		int i_119_ = y & 0x7f;
		int i_117_ = (((i_116_ * Client.tileHeight[transformedHeight][posX + 1][posY]) + (Client.tileHeight[transformedHeight][posX][posY] * (128 - i_116_))) >> 7);
		int i_118_ = (((128 - i_116_)
				* (Client.tileHeight[transformedHeight][posX][posY + 1]) + (Client.tileHeight[transformedHeight][posX + 1][posY + 1])
				* i_116_) >> 7);
		return i_119_ * i_118_ + i_117_ * (128 - i_119_) >> 7;
	}

	static final int method375() {
		int i_3_ = Client.getTileHeight(Client.renderX, renderY, height);
		if (i_3_ - renderZ < 800
				&& ((Client.tileSettings[height][Client.renderX >> 7][renderY >> 7]) & 0x4) != 0)
			return height;
		return 3;
	}

	static final RSString createRSString(byte[] buffer, int off, int len) {
		RSString instance = new RSString();
		instance.length = len;
		instance.buffer = new byte[len];
		ArrayUtils.arraycopy(buffer, off, instance.buffer, 0, len);
		return instance;
	}

	static final void updatePlayers(boolean foreverAlone) {
		if ((Client.playerRenderX >> 7) == flagX
				&& (Client.playerRenderY >> 7) == Client.flagY)
			flagX = 0;
		int playerCount = localPlayerCount;
		if (foreverAlone)
			playerCount = 1;
		for (int id = 0; playerCount > id; id++) {
			Player player;
			int index;
			if (foreverAlone) {
				index = 33538048;
				player = Client.myPlayer;
			} else {
				player = Client.localPlayers[Client.playerIndices[id]];
				index = Client.playerIndices[id] << 14;
			}
			if (player != null && player.isVisible()) {
				player.isIdle = false;
				int x = (player.x) >> 7;
				int y = (player.y) >> 7;
				if ((Client.lowMem && localPlayerCount > 50 || localPlayerCount > 200)
						&& !foreverAlone
						&& (player.idleAnimation == player.currentAnimation))
					player.isIdle = true;
				if (x >= 0 && x < 104 && y >= 0 && y < 104) {
					if ((player.aClass3_Sub3_Sub3_Sub3_2188) == null
							|| (player.anInt2175 > Client.loopCycle)
							|| (Client.loopCycle >= player.anInt2184)) {
						if ((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64) {
							if (flashEffectCycle == anIntArrayArray429[x][y])
								continue;
							anIntArrayArray429[x][y] = flashEffectCycle;
						}
						player.tileHeight = (Client.getTileHeight(player.x,
								player.y, height));
						Client.sceneGraph.render(height, player.x, player.y,
								player.tileHeight, 60, player,
								player.directionDegrees, index,
								player.aBoolean1914);
					} else {
						player.isIdle = false;
						player.tileHeight = (Client.getTileHeight(player.x,
								player.y, height));
						Client.sceneGraph.method974(height, player.x, player.y,
								player.tileHeight, 60, player,
								player.directionDegrees, index,
								player.anInt2183, player.anInt2172,
								player.anInt2177, player.anInt2170);
					}
				}
			}
		}
	}

	static final void clearSpriteCache() {
		Client.itemSpriteCache.clear();
	}

	static final void closeUpdateServer() {
		if (Client.updateServerConnection != null)
			Client.updateServerConnection.close();
	}

	static final IndexedSprite method63(FileSystem class18, RSString class63,
			RSString class63_0_) {
		int i_1_ = class18.getHash(class63);
		int i_2_ = class18.getHash(class63_0_, i_1_);
		return Client.method705(i_1_, i_2_, class18);
	}

	static final void method65(CustomObjectSpawn class3_sub4) {
		int id = -1;
		int hash = 0;
		int i_6_ = 0;
		int i_7_ = 0;
		if (class3_sub4.sceneGraphType == 0)
			hash = Client.sceneGraph.getWallObjectHash(class3_sub4.x,
					class3_sub4.y, class3_sub4.z);
		if (class3_sub4.sceneGraphType == 1)
			hash = Client.sceneGraph.getWallDecorationHash(class3_sub4.x,
					class3_sub4.y, class3_sub4.z);
		if (class3_sub4.sceneGraphType == 2)
			hash = Client.sceneGraph.getInteractiveObjectHash(class3_sub4.x,
					class3_sub4.y, class3_sub4.z);
		if (class3_sub4.sceneGraphType == 3)
			hash = Client.sceneGraph.getGroundDecorationHash(class3_sub4.x,
					class3_sub4.y, class3_sub4.z);
		if (hash != 0) {
			id = hash >> 14 & 0x7fff;
			int i_8_ = (Client.sceneGraph.getUID(class3_sub4.z, class3_sub4.x,
					class3_sub4.y, hash));
			i_6_ = i_8_ & 0x1f;
			i_7_ = (i_8_ & 0xeb) >> 6;
		}
		class3_sub4.anInt1198 = i_6_;
		class3_sub4.anInt1197 = id;
		class3_sub4.anInt1196 = i_7_;
	}

	static final void dropClient() {
		if (Client.logoutTimer > 0)
			Client.processLogout();
		else {
			Client.setGameState(40);
			Client.worldConnection2 = worldConnection;
		}
	}

	static final void resetIkit() {
		iKitCache.clear();
	}
	
    public static final void WriteFile(String s, byte abyte0[])
    {
        try
        {
            (new File((new File(s)).getParent())).mkdirs();
            FileOutputStream fileoutputstream = new FileOutputStream(s);
            fileoutputstream.write(abyte0, 0, abyte0.length);
            fileoutputstream.close();
        }
        catch(Throwable throwable)
        {
            System.out.println((new StringBuilder()).append("Write Error: ").append(s).toString());
        }
    }
    
    public static final byte[] ReadFile(String s)
    {
        try
        {
            File file = new File(s);
            int i = (int)file.length();
            byte abyte0[] = new byte[i];
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            datainputstream.readFully(abyte0, 0, i);
            datainputstream.close();
            return abyte0;
        }
        catch(Exception exception)
        {
        }
        return null;
    }

	public static final int getMapFileId(int floorOrObject, int regionY, int regionX)
	{
		int regionId = (regionX << 8) + regionY;
		for(int j1 = 0; j1 < mapIndices1.length; j1++) {
			if(mapIndices1[j1] == regionId) {
				if(floorOrObject == 0) {
					/*switch(mapIndices2[j1]) {
						//case 6832:
						case 100000:
							System.out.println("Caught: " + mapIndices2[j1]);
							return -1;
					}*/
					//System.out.println(mapIndices2[j1]);
					return mapIndices2[j1];
				} else {
					/*switch(mapIndices3[j1]) {
						case 6833:
						case 100000:
							System.out.println("Caught: " + mapIndices3[j1]);
							return -1;
					}*/
					//System.out.println(mapIndices3[j1]);
					return mapIndices3[j1];
				}
			}
		}
		return -1;
	}

	static private int[] mapIndices1;
	static private int[] mapIndices2;
	static private int[] mapIndices3;

	static int mapAmount = 0;
	public static void unpackMapIndex()
	{
		/*byte[] modelIndex = ReadFile("./cache/map_index");//streamLoader.get("map_index");
		RSByteBuffer stream2 = new RSByteBuffer(modelIndex);
		int mapCount = stream2.getUShort();
		mapIndices1 = new int[mapCount];
		mapIndices2 = new int[mapCount];
		mapIndices3 = new int[mapCount];
		for(int i2 = 0; i2 < mapCount; i2++)
		{
			mapIndices1[i2] = stream2.getUShort();
			mapIndices2[i2] = stream2.getUShort();
			mapIndices3[i2] = stream2.getUShort();

			mapAmount++;
		}*/
	}

	static final void rebuildMap() {
		boolean pass = true;
		String fileName = Client.squareX+"_"+Client.squareY;
		//int fileId = getMapFileId(1, squareY, squareX);
		File file = new File("./639Map/" + Client.currentLandscapeFiles[0] + ".dat");
		File updated = new File("./maps/m"+fileName+".new");
		if(updated.exists())
			Client.landscapeFileBuffer[0] = ReadFile("./maps/m"+fileName+".new");
		else if(file.exists())
			Client.landscapeFileBuffer[0] = ReadFile("./639Map/" + Client.currentLandscapeFiles[0] + ".dat");
		else
			Client.landscapeFileBuffer[0] = cacheIndex5.getXteaBuffer(Client.currentLandscapeFiles[0], 0, new int[] {0, 0, 0, 0});
		//fileId = getMapFileId(0, squareY, squareX);
		file = new File("./639Map/" + Client.currentMapFiles[0] + ".dat");
		updated = new File("./maps/l"+fileName+".new");
		if(updated.exists())
			Client.mapFileBuffer[0] = ReadFile("./maps/l"+fileName+".new");
		else if(file.exists())
			Client.mapFileBuffer[0] = ReadFile("./639Map/" + Client.currentMapFiles[0] + ".dat");
		else
			Client.mapFileBuffer[0] = cacheIndex5.getXteaBuffer(0, xtea[0], Client.currentMapFiles[0]);
		WriteFile("C:\\Users\\Chris\\Desktop\\Zaros\\Zaros_Client\\custommaps/"+Client.currentLandscapeFiles[0] + ".dat", landscapeFileBuffer[0]);
		WriteFile("C:\\Users\\Chris\\Desktop\\Zaros\\Zaros_Client\\custommaps/"+Client.currentMapFiles[0] + ".dat", mapFileBuffer[0]);
		if (!pass) {
			loadingScreenType = 1;
		} else {
			Client.modelFetchCount = 0;
			pass = true;
			for (int id = 0; id < Client.landscapeFileBuffer.length; id++) {
				byte[] buffer = Client.mapFileBuffer[id];
				if (buffer != null) {
					int x = (Client.currentRegions[id] >> 8) * 64
							- Client.currentBaseX;
					int y = (Client.currentRegions[id] & 0xff) * 64
							- Client.currentBaseY;
					if (Client.secondMapRegion) {
						y = 10;
						x = 10;
					}
					pass &= Client.method165(buffer, y, x);
				}
			}
			if (!pass)
				loadingScreenType = 2;
			else {
				if (loadingScreenType != 0)
					Client.drawTextOnScreen(Client.loadingPleaseWait,
							hundredPercent, true);
				Client.clearCache();
				Client.sceneGraph.dispose();
				System.gc();
				for (int z = 0; z < 5; z++)
					Client.collisionMaps[z].resetFlags();
				for (int z = 0; z < 4; z++) {
					for (int x = 0; x < 104; x++) {
						for (int y = 0; y < 104; y++)
							Client.tileSettings[z][x][y] = (byte) 0;
					}
				}
				method661();
				int fileLen = Client.landscapeFileBuffer.length;
				if (!Client.secondMapRegion) {
					for (int id = 0; fileLen > id; id++) {
						int x = (currentRegions[id] >> 8) * 64 - currentBaseX;
						int y = (currentRegions[id] & 0xff) * 64 - currentBaseY;
						byte[] buffer = Client.landscapeFileBuffer[id];
						if (buffer != null)
							Client.method686(Client.oldRegionY * 8 - 48,
									Client.collisionMaps, buffer, y,
									Client.oldRegionX * 8 - 48, x);
					}
					for (int id = 0; id < fileLen; id++) {
						int y = ((Client.currentRegions[id] & 0xff) * 64 - Client.currentBaseY);
						int x = ((Client.currentRegions[id] >> 8) * 64 - Client.currentBaseX);
						byte[] buffer = Client.landscapeFileBuffer[id];
						if (buffer == null && Client.oldRegionY < 800)
							Client.method367(x, 64, y, 64);
					}
					for (int id = 0; id < fileLen; id++) {
						byte[] buffer = Client.mapFileBuffer[id];
						if (buffer != null) {
							int x = ((Client.currentRegions[id] >> 8) * 64 - Client.currentBaseX);
							int y = ((Client.currentRegions[id] & 0xff) * 64 - Client.currentBaseY);
							method156(Client.collisionMaps, Client.sceneGraph,
									x, y, buffer);
						}
					}
				}
				Client.clearCache();
				Client.method599(Client.collisionMaps, Client.sceneGraph);
				int i_49_ = Client.anInt1332;
				if (i_49_ > height)
					i_49_ = height;
				if (height - 1 > i_49_)
					i_49_ = height - 1;
				if (!Client.lowMem)
					Client.sceneGraph.createTiles(0);
				else
					Client.sceneGraph.createTiles(Client.anInt1332);
				Client.method238();
				aClass19_178.clear();
				if (!Client.secondMapRegion) {
					int i_52_ = (Client.oldRegionX - 6) / 8;
					int i_53_ = (Client.oldRegionY - 6) / 8;
					int i_54_ = (Client.oldRegionX + 6) / 8;
					int i_55_ = (Client.oldRegionY + 6) / 8;
					for (int i_56_ = i_52_ - 1; i_54_ + 1 >= i_56_; i_56_++) {
						for (int i_57_ = i_53_ - 1; i_57_ <= i_55_ + 1; i_57_++) {
							if (i_56_ < i_52_ || i_54_ < i_56_ || i_57_ < i_53_
									|| i_57_ > i_55_) {
								cacheIndex5
										.method567(linkRSStrings(new RSString[] {
												m, RSString.valueOf(i_56_),
												Client.underScore,
												RSString.valueOf(i_57_) }));
								cacheIndex5
										.method567(linkRSStrings(new RSString[] {
												Client.l,
												RSString.valueOf(i_56_),
												Client.underScore,
												RSString.valueOf(i_57_) }));
							}
						}
					}
				}
				drawLandscapeOnMinimap(height);
				if (fullScreenChatboxId != -1)
					Client.setGameState(35);
				else
					Client.setGameState(30);
				Client.method170();
				Client.method818();
			}
		}
	}

	static final int method537(int z, int x, int y) {
		//if ((Client.tileSettings[z][x][y] & 0x8) != 0)
		//	return 0;
		//if (z > 0 && (Client.tileSettings[1][x][y] & 0x2) != 0)
		//	return z - 1;
		return z;
	}

	static final void removeMouse(Component component) {
		component.removeMouseListener(Client.mouseHandler);
		component.removeMouseWheelListener(Client.mouseWheel);
		component.removeMouseMotionListener(Client.mouseHandler);
	}

	static final void addMouse(Component component) {
		component.addMouseListener(Client.mouseHandler);
		component.addMouseWheelListener(Client.mouseWheel);
		component.addMouseMotionListener(Client.mouseHandler);
	}

	static final boolean method645(int objectId, int type) {
		if (type == 11)
			type = 10;
		if (type >= 5 && type <= 8)
			type = 4;
		ObjectDefinition class3_sub3_sub14 = ObjectDefinition.forID(objectId);
		return class3_sub3_sub14.method337(type);
	}

	static final void updateEntity(Entity entity) {
		if (entity.x < 128 || entity.y < 128 || entity.x >= 13184
				|| entity.y >= 13184) {
			entity.startCycle = 0;
			entity.currentAnimationId = -1;
			entity.moveCycle = 0;
			entity.currentGraphic = -1;
			entity.x = entity.size * 64 + entity.walkQueueX[0] * 128;
			entity.y = entity.size * 64 + entity.walkQueueY[0] * 128;
			entity.resetWalkingQueue();
		}
		if (Client.myPlayer == entity && entity.x < 1536 || entity.y < 1536
				|| entity.x >= 11776 || entity.y >= 11776) {
			entity.currentAnimationId = -1;
			entity.moveCycle = 0;
			entity.currentGraphic = -1;
			entity.startCycle = 0;
			entity.x = entity.size * 64 + entity.walkQueueX[0] * 128;
			entity.y = entity.size * 64 + entity.walkQueueY[0] * 128;
			entity.resetWalkingQueue();
		}
		moveEntity(entity);
		updateFace(entity);
		updateAnimation(entity);
	}

	static final void determineMenuSize() {
		int chooseOptionWidth = Client.boldFont
				.getStringWidth(Client.chooseOption);
		for (int id = 0; Client.menuActionCount > id; id++) {
			int actionWidth = Client.boldFont
					.getStringWidthColors(Client.menuActionName[id]);
			if (chooseOptionWidth < actionWidth)
				chooseOptionWidth = actionWidth;
		}
		int menuHeight = Client.menuActionCount * 15 + 21;
		chooseOptionWidth += 8;
		if (lastClickX > 0 && lastClickY > 0 && lastClickX < clientFrame.getWidth()
				&& lastClickY < clientFrame.getHeight()) {
			Client.menuOpen = true;
			Client.menuHeight = Client.menuActionCount * 15 + 22;
			Client.menuWidth = chooseOptionWidth;
			menuType = 0;
			int offsetX = (lastClickX) - (chooseOptionWidth / 2);
			if (offsetX + chooseOptionWidth > clientFrame.getWidth())
				offsetX = clientFrame.getWidth() - chooseOptionWidth;
			if (offsetX < 0)
				offsetX = 0;
			int offsetY = lastClickY;
			Client.menuOffsetX = offsetX;
			if (menuHeight + offsetY > clientFrame.getHeight())
				offsetY = clientFrame.getHeight() - menuHeight;
			if (offsetY < 0)
				offsetY = 0;
			Client.menuOffsetY = offsetY;
		}
	}

	static final void doAction(int id) {
		if (id >= 0) {
			int command3 = Client.menuActionCmd3[id];
			int command2 = Client.menuActionCmd2[id];
			int opcode = Client.menuActionOpcode[id];
			if (opcode >= 2000)
				opcode -= 2000;
			int index = Client.menuActionIndex[id];
			
			//System.out.println(command3 + ", " + command2 + ", " + opcode + ", " + index);
			//System.out.println("doAction opcode: " + opcode);
			
			if (Client.inputDialogState != 0 && opcode != 1002) {
				Client.inputDialogState = 0;
				Client.redrawChatbox = true;
			}
			if (opcode == 38) {
				atInventoryInterfaceType = 2;
				Client.focusedItemInterface = command2;
				Client.focusedItemSlot = command3;
				Client.redrawTimer = 0;
				if (Client.unwalkableInterfaceId == command2 >> 16)
					atInventoryInterfaceType = 1;
				if (Client.unwalkableChatboxId == command2 >> 16)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 19) {
				Player player = Client.localPlayers[index];
				if (player != null) {
					Client.doWalkTo(false, playerLocalY, 1, 0, 2, 0, 1,
							player.walkQueueX[0],
							playerLocalX, 0,
							playerLocalY);
					Client.crossX = lastClickX;
					Client.crossIndex = 0;
					Client.crossY = lastClickY;
					Client.crossState = 2;
				}
			}
			if (opcode == 28) {
				Client.redrawChatbox = true;
				walkableChatboxId = -1;
			}
			if (opcode == 21) {
				boolean bool = Client.doWalkTo(false, command2, 0, 0, 2, 0, 0,
						command3, playerLocalX, 0,
						playerLocalY);
				if (!bool)
					bool = Client.doWalkTo(false, command2, 1, 0, 2, 0, 1,
							command3, playerLocalX, 0,
							playerLocalY);
				Client.crossY = lastClickY;
				Client.crossIndex = 0;
				Client.crossX = lastClickX;
				Client.crossState = 2;
			}
			if (opcode == 46) {
				boolean bool = Client.doWalkTo(false, command2, 0, 0, 2, 0, 0,
						command3, playerLocalX, 0,
						playerLocalY);
				if (!bool)
					bool = Client.doWalkTo(false, command2, 1, 0, 2, 0, 1,
							command3, playerLocalX, 0,
							playerLocalY);
				Client.crossX = lastClickX;
				Client.crossIndex = 0;
				Client.crossY = lastClickY;
				Client.crossState = 2;
			}
			if (opcode == 6) {
				Client.focusedItemSlot = command3;
				atInventoryInterfaceType = 2;
				Client.focusedItemInterface = command2;
				if (Client.unwalkableInterfaceId == command2 >> 16)
					atInventoryInterfaceType = 1;
				if (Client.unwalkableChatboxId == command2 >> 16)
					atInventoryInterfaceType = 3;
				Client.redrawTimer = 0;
			}
			if (opcode == 12) {
				Client.focusedItemSlot = command3;
				atInventoryInterfaceType = 2;
				if (Client.unwalkableInterfaceId == command2 >> 16)
					atInventoryInterfaceType = 1;
				if (command2 >> 16 == Client.unwalkableChatboxId)
					atInventoryInterfaceType = 3;
				Client.focusedItemInterface = command2;
				Client.redrawTimer = 0;
			}
			if (opcode == 25) {
				Player player = Client.localPlayers[index];
				if (player != null) {
					Client.doWalkTo(false, playerLocalY, 1, 0, 2, 0, 1,
							player.walkQueueX[0],
							playerLocalX, 0,
							playerLocalY);
					Client.crossIndex = 0;
					Client.crossState = 2;
					Client.crossX = lastClickX;
					Client.crossY = lastClickY;
				}
			}
			if (opcode == 55) {
				RSString name = Client.menuActionName[id];
				int whiteIndex = name.indexOf(Client.whi);
				if (whiteIndex != -1) {
					int friendsIndex = -1;
					long hash = name.substring(whiteIndex + 5).trim().toLong();
					for (int friendIndex = 0; Client.friendsCount > friendIndex; friendIndex++) {
						if (Client.friendList[friendIndex] == hash) {
							friendsIndex = friendIndex;
							break;
						}
					}
					if (friendsIndex != -1
							&& Client.friendsWorlds[friendsIndex] > 0) {
						Client.messagePromptRaised = true;
						Client.privateChatPromptInput = Client.nothing;
						friendsListAction = 3;
						Client.inputDialogState = 0;
						Client.redrawChatbox = true;
						friendHash = Client.friendList[friendsIndex];
						Client.inputMessage = linkRSStrings(new RSString[] {
								enterMessageToSendTo,
								Client.friendsNames[friendsIndex] });
					}
				}
			}
			if (opcode == 56) {
				Client.method113(command2, index, command3);
			}
			if (opcode == 18) {
				atInventoryInterfaceType = 2;
				if (Client.unwalkableInterfaceId == command2 >> 16)
					atInventoryInterfaceType = 1;
				if (Client.unwalkableChatboxId == command2 >> 16)
					atInventoryInterfaceType = 3;
				Client.redrawTimer = 0;
				Client.focusedItemSlot = command3;
				Client.focusedItemInterface = command2;
			}
			if (opcode == 47) {
				Player player = Client.localPlayers[index];
				if (player != null) {
					Client.doWalkTo(false, playerLocalY, 1, 0, 2, 0, 1,
							player.walkQueueX[0],
							playerLocalX, 0,
							playerLocalY);
					Client.crossIndex = 0;
					Client.crossState = 2;
					Client.crossX = lastClickX;
					Client.crossY = lastClickY;
				}
			}
			if (opcode == 3) {
				atInventoryInterfaceType = 2;
				Client.focusedItemInterface = command2;
				Client.redrawTimer = 0;
				Client.focusedItemSlot = command3;
				if (command2 >> 16 == Client.unwalkableInterfaceId)
					atInventoryInterfaceType = 1;
				if (Client.unwalkableChatboxId == command2 >> 16)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 23) {
				boolean bool = (Client.doWalkTo(false, command2, 0, 0, 2, 0, 0,
						command3, playerLocalX, 0,
						playerLocalY));
				if (!bool)
					bool = (Client.doWalkTo(false, command2, 1, 0, 2, 0, 1,
							command3, playerLocalX, 0,
							playerLocalY));
				Client.crossX = lastClickX;
				Client.crossIndex = 0;
				Client.crossY = lastClickY;
				Client.crossState = 2;
			}
			if (opcode == 14) {
				boolean bool = (Client.doWalkTo(false, command2, 0, 0, 2, 0, 0,
						command3, Client.myPlayer.walkQueueX[0], 0,
						Client.myPlayer.walkQueueY[0]));
				if (!bool)
					bool = (Client.doWalkTo(false, command2, 1, 0, 2, 0, 1,
							command3, Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]));
				Client.crossY = lastClickY;
				Client.crossIndex = 0;
				Client.crossX = lastClickX;
				Client.crossState = 2;
			}
			if (opcode == 7) {
				boolean bool = (Client.doWalkTo(false, command2, 0, 0, 2, 0, 0,
						command3, Client.myPlayer.walkQueueX[0], 0,
						Client.myPlayer.walkQueueY[0]));
				if (!bool)
					bool = (Client.doWalkTo(false, command2, 1, 0, 2, 0, 1,
							command3, Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]));
				Client.crossState = 2;
				Client.crossY = lastClickY;
				Client.crossX = lastClickX;
				Client.crossIndex = 0;
			}
			if (opcode == 29) {
				atInventoryInterfaceType = 2;
				Client.redrawTimer = 0;
				if (Client.unwalkableInterfaceId == command2 >> 16)
					atInventoryInterfaceType = 1;
				Client.focusedItemInterface = command2;
				Client.focusedItemSlot = command3;
				if (command2 >> 16 == Client.unwalkableChatboxId)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 52) {
				Player player = Client.localPlayers[index];
				if (player != null) {
					Client.doWalkTo(false, player.walkQueueY[0], 1, 0, 2, 0, 1,
							player.walkQueueX[0],
							Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]);
					Client.crossState = 2;
					Client.crossX = lastClickX;
					Client.crossIndex = 0;
					Client.crossY = lastClickY;
				}
			}
			if (opcode == 34) {
				boolean bool = (Client.doWalkTo(false, command2, 0, 0, 2, 0, 0,
						command3, Client.myPlayer.walkQueueX[0], 0,
						Client.myPlayer.walkQueueY[0]));
				if (!bool)
					bool = (Client.doWalkTo(false, command2, 1, 0, 2, 0, 1,
							command3, Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]));
				Client.crossIndex = 0;
				Client.crossY = lastClickY;
				Client.crossState = 2;
				Client.crossX = lastClickX;
			}
			if (opcode == 13) {
				if (Client.menuOpen)
					Client.sceneGraph.method996(command3, command2);
				else
					Client.sceneGraph.method996(lastClickX, lastClickY);
			}
			if (opcode == 20) {
				atInventoryInterfaceType = 2;
				Client.focusedItemSlot = command3;
				Client.focusedItemInterface = command2;
				Client.redrawTimer = 0;
				if (command2 >> 16 == Client.unwalkableInterfaceId)
					atInventoryInterfaceType = 1;
				if (Client.unwalkableChatboxId == command2 >> 16)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 2) {
				Client.method113(command2, index, command3);
			}
			if (opcode == 22 && !waitForNextChat) {
				waitForNextChat = true;
			}
			if (opcode == 5) {
				Player player = Client.localPlayers[index];
				if (player != null) {
					Client.doWalkTo(false, player.walkQueueY[0], 1, 0, 2, 0, 1,
							player.walkQueueX[0],
							Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]);
					Client.crossIndex = 0;
					Client.crossState = 2;
					Client.crossY = lastClickY;
					Client.crossX = lastClickX;
				}
			}
			if (opcode == 50) {
				Npc npc = Client.localNpcs[index];
				if (npc != null) {
					Client.doWalkTo(false, ((Entity) npc).walkQueueY[0], 1, 0,
							2, 0, 1, ((Entity) npc).walkQueueX[0],
							Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]);
					Client.crossState = 2;
					Client.crossY = lastClickY;
					Client.crossIndex = 0;
					Client.crossX = lastClickX;
				}
			}
			if (opcode == 49) {
				Client.focusedItemInterface = command2;
				Client.redrawTimer = 0;
				atInventoryInterfaceType = 2;
				if (Client.unwalkableInterfaceId == command2 >> 16)
					atInventoryInterfaceType = 1;
				Client.focusedItemSlot = command3;
				if (command2 >> 16 == Client.unwalkableChatboxId)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 31) {
				Player player = Client.localPlayers[index];
				if (player != null) {
					Client.doWalkTo(false, ((Entity) player).walkQueueY[0], 1,
							0, 2, 0, 1, ((Entity) player).walkQueueX[0],
							Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]);
					Client.crossY = lastClickY;
					Client.crossX = lastClickX;
					Client.crossState = 2;
					Client.crossIndex = 0;
				}
			}
			if (opcode == 24 || opcode == 35) {
				RSString class63 = Client.menuActionName[id];
				int i_10_ = class63.indexOf(Client.whi);
				if (i_10_ != -1) {
					class63 = class63.substring(i_10_ + 5).trim();
					RSString class63_11_ = class63.method916().upperCase();
					boolean bool = false;
					for (int i_12_ = 0; localPlayerCount > i_12_; i_12_++) {
						Player player = (Client.localPlayers[Client.playerIndices[i_12_]]);
						if (player != null && (player.name != null)
								&& player.name.equalsIgnoreCase(class63_11_)) {
							Client.doWalkTo(false, player.walkQueueY[0], 1, 0,
									2, 0, 1, player.walkQueueX[0],
									Client.myPlayer.walkQueueX[0], 0,
									Client.myPlayer.walkQueueY[0]);
							bool = true;
							break;
						}
					}
				}
			}
			if (opcode == 11) {
				atInventoryInterfaceType = 2;
				Client.focusedItemInterface = command2;
				if (command2 >> 16 == Client.unwalkableInterfaceId)
					atInventoryInterfaceType = 1;
				Client.redrawTimer = 0;
				if (command2 >> 16 == Client.unwalkableChatboxId)
					atInventoryInterfaceType = 3;
				Client.focusedItemSlot = command3;
			}
			if (opcode == 16) {
				Client.focusedItemInterface = command2;
				Client.redrawTimer = 0;
				Client.focusedItemSlot = command3;
				atInventoryInterfaceType = 2;
				if (command2 >> 16 == Client.unwalkableInterfaceId)
					atInventoryInterfaceType = 1;
				if (command2 >> 16 == Client.unwalkableChatboxId)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 10) {
				boolean bool = (Client.doWalkTo(false, command2, 0, 0, 2, 0, 0,
						command3, Client.myPlayer.walkQueueX[0], 0,
						Client.myPlayer.walkQueueY[0]));
				if (!bool)
					bool = (Client.doWalkTo(false, command2, 1, 0, 2, 0, 1,
							command3, Client.myPlayer.walkQueueX[0], 0,
							Client.myPlayer.walkQueueY[0]));
				Client.crossX = lastClickX;
				Client.crossY = lastClickY;
				Client.crossState = 2;
				Client.crossIndex = 0;
			}
			if (opcode == 36) {
				atInventoryInterfaceType = 2;
				Client.focusedItemInterface = command2;
				if (Client.unwalkableInterfaceId == command2 >> 16)
					atInventoryInterfaceType = 1;
				Client.redrawTimer = 0;
				Client.focusedItemSlot = command3;
				if (command2 >> 16 == Client.unwalkableChatboxId)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 42) {
				Client.redrawTimer = 0;
				Client.focusedItemSlot = command3;
				atInventoryInterfaceType = 2;
				Client.focusedItemInterface = command2;
				if (command2 >> 16 == Client.unwalkableInterfaceId)
					atInventoryInterfaceType = 1;
				if (Client.unwalkableChatboxId == command2 >> 16)
					atInventoryInterfaceType = 3;
			}
			if (opcode == 41) {
				Npc npc = (Client.localNpcs[index]);
				if (npc != null) {
					Client.doWalkTo(false, npc.walkQueueY[0], 1, 0, 2, 0, 1,
							npc.walkQueueX[0], Client.myPlayer.walkQueueX[0],
							0, Client.myPlayer.walkQueueY[0]);
					Client.crossY = lastClickY;
					Client.crossIndex = 0;
					Client.crossState = 2;
					Client.crossX = lastClickX;
				}
			}
		}
	}

	static final void initGameScreen() {
		Client.gameScreenBuffer.initDrawingArea();
		Client.gamescreenAreaOffsets = Rasterizer
				.initOffsets(Client.gamescreenAreaOffsets);
	}

	static final void method402(int z, int i_1_, int i_2_) {
		for (int i_5_ = 0; i_5_ < 8; i_5_++) {
			for (int i_6_ = 0; i_6_ < 8; i_6_++)
				Client.tileHeight[z][i_2_ + i_5_][i_1_ + i_6_] = 0;
		}
		if (i_2_ > 0) {
			for (int i_7_ = 1; i_7_ < 8; i_7_++)
				Client.tileHeight[z][i_2_][i_7_ + i_1_] = (Client.tileHeight[z][i_2_ - 1][i_7_
						+ i_1_]);
		}
		if (i_1_ > 0) {
			for (int i_8_ = 1; i_8_ < 8; i_8_++)
				Client.tileHeight[z][i_2_ + i_8_][i_1_] = (Client.tileHeight[z][i_8_
						+ i_2_][i_1_ - 1]);
		}
		if (i_2_ > 0 && Client.tileHeight[z][i_2_ - 1][i_1_] != 0)
			Client.tileHeight[z][i_2_][i_1_] = Client.tileHeight[z][i_2_ - 1][i_1_];
		else if (i_1_ > 0 && Client.tileHeight[z][i_2_][i_1_ - 1] != 0)
			Client.tileHeight[z][i_2_][i_1_] = Client.tileHeight[z][i_2_][i_1_ - 1];
		else if (i_2_ > 0 && i_1_ > 0
				&& (Client.tileHeight[z][i_2_ - 1][i_1_ - 1] != 0))
			Client.tileHeight[z][i_2_][i_1_] = Client.tileHeight[z][i_2_ - 1][i_1_ - 1];
	}

	static final boolean textureExists(FileSystem fetcher, int id) {
		byte[] buffer = fetcher.method573(id);
		if (buffer == null)
			return false;
		unpackSprites(buffer);
		return true;
	}

	static final void drawGameFrame() {
		Client.draw3DScreen();
		if(GUI.layeredPane.getSelectedIndex() == 0)
			Client.drawMinimap();//TODO
		cycleRate = 0;
	}

	static final void method156(CollisionMap[] collisionmap,
			SceneGraph scenegraph, int x, int y, byte[] buffer) {
		objects.clear();
		x = 0; // TODO: Testing
		y = 0;
		RSByteBuffer stream = new RSByteBuffer(buffer);
		int objectId = -1;
		for (;;) {
			int i_4_ = stream.getUSmart();
			if (i_4_ == 0)
				break;
			objectId += i_4_;
			int i_5_ = 0;
			for (;;) {
				int i_6_ = stream.getUSmart();
				if (i_6_ == 0)
					break;
				i_5_ += i_6_ - 1;
				int i_7_ = i_5_ & 0x3f;
				int i_8_ = i_5_ >> 6 & 0x3f;
				int height = i_5_ >> 12;
				int i_10_ = stream.getUByte();
				int type = i_10_ >> 2;
				int rotation = i_10_ & 0x3;
				int posY = i_7_ + y;
				int posX = i_8_ + x;
				if (posX > 0 && posY > 0 && posX < 103 && posY < 103) {
					if (height >= 4)
						continue;
					GameObject obj = new GameObject(objectId, i_8_, i_7_, height, type, rotation);
					objects.put(obj.hash(), obj);
					int i_15_ = height;
					CollisionMap class59 = null;
					if (((Client.tileSettings[1][posX][posY]) & 0x2) == 2) {
						i_15_--;
					}
					if (i_15_ >= 0)
						class59 = collisionmap[i_15_];
					Client.method1006(posX, class59, rotation, objectId,
							height, scenegraph, type, posY);
				}
			}
		}
	}

	static final IndexedSprite[] constructIndexedSprites() {
		IndexedSprite[] sprites = new IndexedSprite[Client.spriteLength];
		for (int id = 0; id < Client.spriteLength; id++) {
			IndexedSprite sprite = sprites[id] = new IndexedSprite();
			sprite.trimWidth = Client.spriteTrimWidth;
			sprite.trimHeight = Client.spriteTrimHeight;
			sprite.offsetX = spriteOffsetX[id];
			sprite.offsetY = Client.spriteOffsetY[id];
			sprite.width = spriteWidth[id];
			sprite.height = Client.spriteHeight[id];
			sprite.pallete = Client.spritePallete;
			sprite.pixels = Client.spritePixels[id];
		}
		Client.resetSprites();
		return sprites;
	}

	static final RSString linkRSStrings(RSString[] class63s) {
		if (class63s.length < 2)
			throw new IllegalArgumentException();
		return RSString.merge(class63s, 0, class63s.length);
	}

	static final void loadGameframe(Component component) {
		try {
			minimapBuffer = constructGraphicsBuffer(component, component.getWidth(), component.getHeight());
			Graphics2D.resetpixels();
			landscapeDestination = new int[151];
			compassSource = new int[33];
			landscapeSource = new int[151];
			compassDestination = new int[33];
			for (int id = 0; id < 33; id++) {
				int i_40_ = 999;
				int i_41_ = 0;
				for (int i_42_ = 0; i_42_ < 34; i_42_++) {
					if (mapBack.pixels[(mapBack.width * id) + i_42_] != 0) {
						if (i_40_ != 999) {
							i_41_ = i_42_;
							break;
						}
					} else if (i_40_ == 999)
						i_40_ = i_42_;
				}
				compassSource[id] = i_40_;
				compassDestination[id] = i_41_ - i_40_;
			}
			for (int id = 5; id < 156; id++) {
				int i_44_ = 999;
				int i_45_ = 0;
				for (int i_46_ = 25; i_46_ < 172; i_46_++) {
					if (mapBack.pixels[i_46_ + mapBack.width * id] != 0
							|| i_46_ <= 34 && id <= 34) {
						if (i_44_ != 999) {
							i_45_ = i_46_;
							break;
						}
					} else if (i_44_ == 999)
						i_44_ = i_46_;
				}
				landscapeSource[id - 5] = i_44_ - 25;
				landscapeDestination[id - 5] = i_45_ - i_44_;
			}
		} catch(Exception e) {
			
		}
	}

	static final void sleep(long time) {
		if (time > 0L) {
			if (time % 10L != 0L)
				Client.threadSleep(time);
			else {
				Client.threadSleep(time - 1L);
				Client.threadSleep(1L);
			}
		}
	}

	static final int getKeyChar(KeyEvent keyevent) {
		int keyChar = keyevent.getKeyChar();
		if (keyChar <= 0 || keyChar >= 256)
			keyChar = -1;
		return keyChar;
	}

	static final int method413(int i, int i_1_) {
		int i_2_ = i_1_ * 57 + i;
		i_2_ = i_2_ << 13 ^ i_2_;
		int i_3_ = i_2_ * (i_2_ * i_2_ * 15731 + 789221) + 1376312589
				& 0x7fffffff;
		return i_3_ >> 19 & 0xff;
	}

	final void processMainGameLoop() {
		if (Client.gameState == 30 || Client.gameState == 35) {
			/*System.out.println(drawTextOnScreen);
			if (Client.drawTextOnScreen && Client.gameState == 30) {
				Client.lastMouseAction = 0;
				lastMouseDragAction = 0;
				while (Client.handleKeyboardInput()) {
					/* empty */
				/*}
				for (int i_8_ = 0; Client.heldKeys.length > i_8_; i_8_++)
					Client.heldKeys[i_8_] = false;
			}*/
			if (Client.lastMouseAction != 0) {
				long time = (Client.lastClickTime - Client.lastlastClickTime) / 50L;
				int x = Client.lastClickX;
				int y = Client.lastClickY;
				if (x < 0) {
					x = 0;
				} else if (x > 764)
					x = 764;
				if (y < 0) {
					y = 0;
				} else if (y > 502)
					y = 502;
				if (time > 4095L)
					time = 4095L;
				Client.lastlastClickTime = Client.lastClickTime;
				int coords = x + y * 765;
				int button = 0;
				if (Client.lastMouseAction == 2)
					button = 1;
				int timeAsInt = (int) time;
			}
			if (Client.cameraPacketDelay > 0)
				Client.cameraPacketDelay--;
			if (Client.heldKeys[96] || Client.heldKeys[97] || Client.heldKeys[98] || Client.heldKeys[99])
				Client.holdingArrowKeys = true;
			/*if (Client.holdingArrowKeys && Client.cameraPacketDelay <= 0) {
				Client.cameraPacketDelay = 20;
				Client.holdingArrowKeys = false;
			}*/
			Client.processLoadState();
			if (Client.gameState == 30 || Client.gameState == 35) {
				Client.processCustomObjects();
				Client.cycleRate++;
				if (Client.crossState != 0) {
					Client.crossIndex += 20;
					if (Client.crossIndex >= 400)
						Client.crossState = 0;
				}
				if (Client.activeInterfaceType != 0) {
					anInt1442++;
					if (lastMouseX > Client.anInt308 + 5
							|| (lastMouseX < Client.anInt308 - 5)
							|| (Client.anInt884 + 5 < Client.lastMouseY)
							|| (Client.lastMouseY < Client.anInt884 - 5))
						Client.aBoolean311 = true;
					if (lastMouseDragAction == 0) {
						if (Client.activeInterfaceType == 2)
							Client.redrawTabArea = true;
						if (Client.activeInterfaceType == 3)
							Client.redrawChatbox = true;
						Client.activeInterfaceType = 0;
						if (!Client.aBoolean311 || (anInt1442 < 5)) {
							if ((Client.mouseButtons == 1)
									&& Client.menuActionCount > 2)
								Client.determineMenuSize();
							else if (Client.menuActionCount > 0)
								Client.doAction((Client.menuActionCount - 1));
						} else {
							Client.anInt964 = -1;
							Client.processMouse();
						}
						Client.lastMouseAction = 0;
						Client.redrawTimer = 10;
					}
				}
				if (SceneGraph.tileClickX != -1) {
					int y = SceneGraph.tileClickY;
					int x = SceneGraph.tileClickX;
					/*boolean cross = Client.doWalkTo(true, y, 0, 0, 0, 0, 0, x,
							playerRenderX, 0,
							playerRenderY);*/
					boolean cross = false;
					if (cross) {
						Client.crossY = Client.lastClickY;
						Client.crossIndex = 0;
						Client.crossState = 1;
						Client.crossX = Client.lastClickX;
					}
					SceneGraph.tileClickX = -1;
				}
				if (Client.lastMouseAction == 1
						&& Client.walkableChatboxString != null) {
					Client.lastMouseAction = 0;
					Client.redrawChatbox = true;
					Client.walkableChatboxString = null;
				}
				processMenuClick();
				if (fullScreenChatboxId == -1) {
					processMinimapClick();
				}
				if (lastMouseDragAction == 1 || Client.lastMouseAction == 1)
					Client.scrollPosition++;
				if (Client.chatboxYellowbox == -1 && tabareaYellowbox == -1
						&& Client.gamescreenYellowBox == -1) {
					if (Client.yellowBoxHoverTimer > 0)
						Client.yellowBoxHoverTimer--;
				} else if (Client.fifty > Client.yellowBoxHoverTimer) {
					Client.yellowBoxHoverTimer++;
					if (Client.yellowBoxHoverTimer == Client.fifty) {
						if (Client.chatboxYellowbox != -1)
							Client.redrawChatbox = true;
						if (tabareaYellowbox != -1)
							Client.redrawTabArea = true;
					}
				}
				Client.method210();
				if (updateCamera)
					Client.method162();
				processInput();
				int idleTimer = Client.mouseIdleTime++;
				int idleTimer2 = keyboardIdleTime++;
				if (idleTimer > 4500 && idleTimer2 > 4500) {
					Client.logoutTimer = 250;
					Client.mouseIdleTime = 4000;
				}
			}
		}
	}

	static final String method545(Throwable throwable) throws IOException {
		String string;
		if (throwable instanceof RuntimeException_Sub1) {
			RuntimeException_Sub1 runtimeexception_sub1 = (RuntimeException_Sub1) throwable;
			string = new StringBuilder(runtimeexception_sub1.errorMessage)
					.append(" | ").toString();
			throwable = runtimeexception_sub1.throwable;
		} else
			string = "";
		StringWriter stringwriter = new StringWriter();
		PrintWriter printwriter = new PrintWriter(stringwriter);
		throwable.printStackTrace(printwriter);
		printwriter.close();
		String string_1_ = stringwriter.toString();
		BufferedReader bufferedreader = new BufferedReader(new StringReader(
				string_1_));
		String string_2_ = bufferedreader.readLine();
		for (;;) {
			String string_3_ = bufferedreader.readLine();
			if (string_3_ == null)
				break;
			int i = string_3_.indexOf('(');
			int i_4_ = string_3_.indexOf(')', i + 1);
			if (i >= 0 && i_4_ >= 0) {
				String string_5_ = string_3_.substring(i + 1, i_4_);
				int i_6_ = string_5_.indexOf(".java:");
				if (i_6_ >= 0) {
					string_5_ = new StringBuilder(string_5_.substring(0, i_6_))
							.append(string_5_.substring(i_6_ + 5)).toString();
					string = new StringBuilder(string).append(string_5_)
							.append(' ').toString();
					continue;
				}
				string_3_ = string_3_.substring(0, i);
			}
			string_3_ = string_3_.trim();
			string_3_ = string_3_.substring(string_3_.lastIndexOf(' ') + 1);
			string_3_ = string_3_.substring(string_3_.lastIndexOf('\t') + 1);
			string = new StringBuilder(string).append(string_3_).append(' ')
					.toString();
		}
		string = new StringBuilder(string).append("| ").append(string_2_)
				.toString();
		return string;
	}

	static final void method568() {
		synchronized (anObject821) {
			if (anInt1465 == 0)
				gameSignlink.startThread(new UpdateServerThread(), 5);
			anInt1465 = 600;
		}
	}

	static final void nullMouse() {
		if (Client.mouseHandler != null) {
			synchronized (Client.mouseHandler) {
				Client.mouseHandler = null;
			}
		}
	}

	static final void processEntityText() {
		for (int id = -1; id < Client.localPlayerCount; id++) {
			int index;
			if (id == -1)
				index = 2047;
			else
				index = Client.playerIndices[id];
			Player player = Client.localPlayers[index];
			if (player != null && player.textCycle > 0) {
				player.textCycle--;
				if (player.textCycle == 0)
					player.textSpoken = null;
			}
		}
		for (int id = 0; Client.localNpcCount > id; id++) {
			int index = Client.localNpcIndices[id];
			Npc npc = Client.localNpcs[index];
			if (npc != null && npc.textCycle > 0) {
				npc.textCycle--;
				if (npc.textCycle == 0)
					npc.textSpoken = null;
			}
		}
	}

	static final void initHuffman(Huffman huffman_) {
		huffman = huffman_;
	}

	static final void handleKeyboard() {
		synchronized (keyboardHandler) {
			keyCacheLen = Client.anInt125;
			if (Client.currentKeyCode >= 0) {
				while (Client.currentKeyCode != Client.anInt290) {
					int i_0_ = (Client.keyCodeCache[Client.anInt290]);
					Client.anInt290 = Client.anInt290 + 1 & 0x7f;
					if (i_0_ >= 0)
						Client.heldKeys[i_0_] = true;
					else
						Client.heldKeys[i_0_ ^ 0xffffffff] = false;
				}
			} else {
				for (int i_1_ = 0; i_1_ < 112; i_1_++)
					Client.heldKeys[i_1_] = false;
				Client.currentKeyCode = Client.anInt290;
			}
			Client.anInt125 = Client.anInt1361;
		}
	}

	static final int method656(int i, int i_1_, int i_2_, int i_3_) {
		int i_4_ = (65536 - Rasterizer.cosineTable[i_2_ * 1024 / i] >> 1);
		return ((65536 - i_4_) * i_1_ >> 16) + (i_3_ * i_4_ >> 16);
	}

	static final void unpackSprites(byte[] buffer) {
		RSByteBuffer stream = new RSByteBuffer(buffer);
		stream.index = buffer.length - 2;
		spriteLength = stream.getUShort();
		spriteHeight = new int[Client.spriteLength];
		spriteWidth = new int[Client.spriteLength];
		spriteOffsetX = new int[Client.spriteLength];
		spritePixels = new byte[Client.spriteLength][];
		spriteOffsetY = new int[Client.spriteLength];
		stream.index = buffer.length - (Client.spriteLength * 8) - 7;
		spriteTrimWidth = stream.getUShort();
		spriteTrimHeight = stream.getUShort();
		int palleteSize = (stream.getUByte() & 0xff) + 1;
		for (int id = 0; Client.spriteLength > id; id++)
			spriteOffsetX[id] = stream.getUShort();
		for (int id = 0; id < Client.spriteLength; id++)
			Client.spriteOffsetY[id] = stream.getUShort();
		for (int id = 0; id < Client.spriteLength; id++)
			spriteWidth[id] = stream.getUShort();
		for (int id = 0; Client.spriteLength > id; id++)
			Client.spriteHeight[id] = stream.getUShort();
		stream.index = buffer.length - (Client.spriteLength * 8)
				- ((palleteSize - 1) * 3 + 7);
		Client.spritePallete = new int[palleteSize];
		for (int id = 1; palleteSize > id; id++) {
			Client.spritePallete[id] = stream.getTriByte();
			if (Client.spritePallete[id] == 0)
				Client.spritePallete[id] = 1;
		}
		stream.index = 0;
		for (int len = 0; len < Client.spriteLength; len++) {
			int width = spriteWidth[len];
			int height = Client.spriteHeight[len];
			int pixelsLen = width * height;
			byte[] pixels = new byte[pixelsLen];
			Client.spritePixels[len] = pixels;
			int imageType = stream.getUByte();
			if (imageType == 0) {
				for (int id = 0; pixelsLen > id; id++)
					pixels[id] = stream.getByte();
			} else if (imageType == 1) {
				for (int offX = 0; offX < width; offX++) {
					for (int offY = 0; height > offY; offY++)
						pixels[width * offY + offX] = stream.getByte();
				}
			}
		}
	}

	static final void method661() {
		Client.aByteArrayArrayArray12 = new byte[4][105][105];
		Client.anInt1332 = 99;
		Client.groundsLigtness = new int[104];
		Client.underlayShapes = new byte[4][104][104];
		Client.heightMod = new boolean[4][104][104];
		Client.groundSaturations = new int[104];
		Client.overlayIds = new byte[4][104][104];
		Client.groundHues = new int[104];
		Client.tileBrightness = new int[105][105];
		groundAlphas = new int[104];
		Client.anIntArray574 = new int[104];
		Client.anIntArrayArrayArray438 = new int[4][105][105];
		Client.underlayIds = new byte[4][104][104];
		underlayRotations = new byte[4][104][104];
	}

	static final void drawRune(IndexedSprite sprite) {
		for (int i_26_ = 0; Client.anIntArray9.length > i_26_; i_26_++)
			Client.anIntArray9[i_26_] = 0;
		int i_27_ = 256;
		for (int i_28_ = 0; i_28_ < 5000; i_28_++) {
			int i_29_ = (int) (Math.random() * 128.0 * (double) i_27_);
			Client.anIntArray9[i_29_] = (int) (Math.random() * 256.0);
		}
		for (int i_30_ = 0; i_30_ < 20; i_30_++) {
			for (int i_31_ = 1; i_27_ - 1 > i_31_; i_31_++) {
				for (int i_32_ = 1; i_32_ < 127; i_32_++) {
					int i_33_ = i_32_ + (i_31_ << 7);
					Client.anIntArray396[i_33_] = (Client.anIntArray9[i_33_ + 128] + (Client.anIntArray9[i_33_ - 1] + (Client.anIntArray9[i_33_ + 1] + Client.anIntArray9[i_33_ - 128]))) / 4;
				}
			}
			int[] is = Client.anIntArray9;
			Client.anIntArray9 = Client.anIntArray396;
			Client.anIntArray396 = is;
		}
		if (sprite != null) {
			int pixelId = 0;
			for (int h = 0; h < sprite.height; h++) {
				for (int w = 0; sprite.width > w; w++) {
					if (sprite.pixels[pixelId++] != 0) {
						int i_38_ = sprite.offsetX + 16 + w;
						int i_39_ = sprite.offsetY + 16 + h;
						int i_40_ = i_38_ + (i_39_ << 7);
						Client.anIntArray9[i_40_] = 0;
					}
				}
			}
		}
	}

	static final void addKeyboard(Component component) {
		try {
			Method method = Signlink.traversalKey;
			if (method != null)
				method.invoke(component, new Object[] { Boolean.FALSE });
		} catch (Throwable throwable) {
			/* empty */
		}
		component.addKeyListener(Client.keyboardHandler);
		component.addFocusListener(Client.keyboardHandler);
	}

	static final Sprite constructSprite() {
		Sprite sprite = new Sprite();
		sprite.trimWidth = spriteTrimWidth;
		sprite.trimHeight = spriteTrimHeight;
		sprite.offsetX = spriteOffsetX[0];
		sprite.offsetY = spriteOffsetY[0];
		sprite.width = spriteWidth[0];
		sprite.height = spriteHeight[0];
		int pixels = sprite.width * sprite.height;
		byte[] pixels_ = spritePixels[0];
		sprite.pixels = new int[pixels];
		for (int id = 0; id < pixels; id++)
			sprite.pixels[id] = Client.spritePallete[255 & pixels_[id]];
		Client.resetSprites();
		return sprite;
	}

	static final void nullKeyboard() {
		if (Client.keyboardHandler != null) {
			synchronized (Client.keyboardHandler) {
				Client.keyboardHandler = null;
			}
		}
	}

	static final void setupOverlayFetcher(FileSystem fetcher) {
		Client.overlayFetcher = fetcher;
	}

	static final void initMinimapBuffer() {
		Client.minimapBuffer.initDrawingArea();
	}

	static final void drawHintOnMinimap(Sprite sprite, int x, int y) {
		int dist = y * y + x * x;
		if (dist > 4225 && dist < 90000) {
			int i_14_ = Client.cameraYaw & 0x7ff;
			int i_15_ = Model.cosine[i_14_];
			int i_16_ = Model.sine[i_14_];
			int i_17_ = y * i_15_ - x * i_16_ >> 16;
			int i_18_ = i_15_ * x + i_16_ * y >> 16;
			double d = Math.atan2((double) i_18_, (double) i_17_);
			int i_19_ = (int) (Math.sin(d) * 63.0);
			int i_20_ = (int) (Math.cos(d) * 57.0);
			Client.mapEdge.drawShapedSprite(i_19_ + 94 - 6, -i_20_ + 83 - 20,
					20, 20, 15, 15, d, 256);
		} else
			Client.markMinimap(sprite, x, y);
	}

	static final synchronized byte[] getPooledBuffer(int len) {
		if (len == 100 && Client.pooledBuffer1Len > 0) {
			byte[] buffer = Client.pooledBuffer1[--Client.pooledBuffer1Len];
			Client.pooledBuffer1[Client.pooledBuffer1Len] = null;
			return buffer;
		}
		if (len == 5000 && Client.pooledBuffer2Len > 0) {
			byte[] buffer = Client.pooledBuffer2[--Client.pooledBuffer2Len];
			Client.pooledBuffer2[Client.pooledBuffer2Len] = null;
			return buffer;
		}
		if (len == 30000 && Client.pooledBuffer3Len > 0) {
			byte[] buffer = Client.pooledBuffer3[--Client.pooledBuffer3Len];
			pooledBuffer3[Client.pooledBuffer3Len] = null;
			return buffer;
		}
		return new byte[len];
	}

	static final void updateFace(Entity entity) {
		if (entity.turnValue != 0) {
			if (entity.interactingEntityIndex != -1
					&& entity.interactingEntityIndex < 32768) {
				Npc npc = Client.localNpcs[entity.interactingEntityIndex];
				if (npc != null) {
					int x = entity.x - npc.x;
					int y = entity.y - npc.y;
					if (x != 0 || y != 0)
						entity.turnDirection = (int) (Math.atan2((double) x,
								(double) y) * 325.949) & 0x7ff;
				}
			}
			if (entity.interactingEntityIndex >= 32768) {
				int index = entity.interactingEntityIndex - 32768;
				if (index == Client.playerIndex)
					index = 2047;
				Player player = Client.localPlayers[index];
				if (player != null) {
					int x = entity.x - player.x;
					int y = entity.y - player.y;
					if (x != 0 || y != 0)
						entity.turnDirection = (int) (Math.atan2((double) x,
								(double) y) * 325.949) & 0x7ff;
				}
			}
			if ((entity.faceX != 0 || (entity.faceY != 0))
					&& ((entity.walkQueueLocationIndex == 0) || (entity.anInt1866) > 0)) {
				int x = ((entity.x) - (-Client.currentBaseX + (-Client.currentBaseX + (entity.faceX))) * 64);
				int y = ((entity.y) - (-Client.currentBaseY + (entity.faceY) - Client.currentBaseY) * 64);
				if (x != 0 || y != 0)
					entity.turnDirection = (int) (Math.atan2((double) x,
							(double) y) * 325.949) & 0x7ff;
				entity.faceY = 0;
				entity.faceX = 0;
			}
			int direction = ((-entity.directionDegrees + (entity.turnDirection)) & 0x7ff);
			if (direction != 0) {
				if (direction < (entity.turnValue)
						|| -(entity.turnValue) + 2048 < direction)
					entity.directionDegrees = (entity.turnDirection);
				else if (direction > 1024)
					entity.directionDegrees -= (entity.turnValue);
				else
					entity.directionDegrees += (entity.turnValue);
				entity.directionDegrees &= 0x7ff;
				if ((entity.idleAnimation == (entity.currentAnimation))
						&& ((entity.directionDegrees) != (entity.turnDirection))) {
					if ((entity.turnAnimation) == -1)
						entity.currentAnimation = (entity.walkAnimation);
					else
						entity.currentAnimation = (entity.turnAnimation);
				}
			}
		}
	}

	static final void setupItemFetcher(FileSystem class18, FileSystem class18_8_) {
		Client.itemModelFetcher = class18;
		Client.itemFetcher = class18_8_;
		Client.totalItems = Client.itemFetcher.getChildCount(10);
	}

	static final void initClassCheckCache() {
		Client.classCheckCache = new Deque();
	}

	static final IndexedSprite[] fetchIndexedSprites(int i, int i_10_,
			FileSystem class18) {
		if (!Client.spriteExists(class18, i, i_10_))
			return null;
		return Client.constructIndexedSprites();
	}

	static final void updatePlayers() {
		for (int id = -1; Client.localPlayerCount > id; id++) {
			int index;
			if (id != -1)
				index = Client.playerIndices[id];
			else
				index = 2047;
			Player player = Client.localPlayers[index];
			if (player != null)
				Client.updateEntity(player);
		}
	}

	static final void handleRequets() {
		for (;;) {
			UpdateServerNode node;
			synchronized (Client.updateServerList) {
				node = (UpdateServerNode) Client.aClass60_2164.popFront();
			}
			if (node == null)
				break;
			node.fileWorker.method596(node.data, false, (int) node.hash,
					node.cache);
		}
	}

	static final Sprite getSingleSprite(FileSystem class18, RSString class63,
			RSString class63_0_) {
		int i_1_ = class18.getHash(class63_0_);
		int i_2_ = class18.getHash(class63, i_1_);
		return Client.fetchSingleSprite(i_2_, class18, i_1_);
	}

	static final void readyForLogin() {
		Client.windowFocused = true;
		Client.lastlastClickTime = 0L;
		Client.coordCounter = 0;
		Client.awtFocus = true;
		Client.initClassCheckCache();
		Client.fourthOpcode = -1;
		Client.hintIconID = 0;
		Client.systemUpdateTime = 0;
		Client.menuActionCount = 0;
		Client.timoutTimer = 0;
		Client.previousOpcode = -1;
		Client.menuOpen = false;
		Client.thirdOpcode = -1;
		Client.packetOpcode = -1;
		Client.logoutTimer = 0;
		Client.mouseIdleTime = 0;
		for (int i_0_ = 0; i_0_ < 100; i_0_++)
			Client.chatMessages[i_0_] = null;
		Client.soundCount = 0;
		Client.minimapHeight = -1;
		Client.minimapBlackout = 0;
		Client.cameraYaw = (int) (Math.random() * 20.0) - 10 & 0x7ff;
		flagX = 0;
		Client.flagY = 0;
		Client.localNpcCount = 0;
		Client.localPlayerCount = 0;
		for (int i_1_ = 0; i_1_ < 2048; i_1_++) {
			Client.localPlayers[i_1_] = null;
			Client.playerAppearanceBuffers[i_1_] = null;
		}
		for (int i_2_ = 0; i_2_ < 16384; i_2_++)
			Client.localNpcs[i_2_] = null;
		Client.myPlayer = Client.localPlayers[2047] = new Player();
		Client.projectileList.clear();
		Client.stillGraphicList.clear();
		Client.customObjectSpawns = new Deque();
		Client.friendsCount = 0;
		Client.friendStatus = 0;
		Client.walkableChatboxId = -1;
		Client.unwalkableChatboxId = -1;
		Client.unwalkableInterfaceId = -1;
		Client.fullScreenChatboxId = -1;
		Client.fullScreenInterfaceId = -1;
		Client.walkableInventoryId = -1;
		Client.walkableChatboxString = null;
		Client.blinkingTabID = -1;
		Client.tabID = 3;
		Client.menuOpen = false;
		Client.multiIcon = 0;
		Client.inputDialogState = 0;
		Client.walkableInterfaceId = -1;
		Client.messagePromptRaised = false;
		Client.waitForNextChat = false;
		Client.playerAppearance.setAppearance(new int[5], -1, false, null);
		for (int id = 0; id < 5; id++) {
			Client.playerActions[id] = null;
			Client.playerActionsOnTop[id] = false;
		}
	}

	static {
		int output = 0;
		for (int lvl = 0; lvl < 99; lvl++) {
			int nextLvl = lvl + 1;
			int points = (int) (Math.pow(2.0, (double) nextLvl / 7.0) * 300.0 + (double) nextLvl);
			output += points;
			xpForLevel[lvl] = output / 4;
		}
		for (int i1 = 0; i1 < 256; i1++) {
			int i_0_ = i1;
			for (int i_1_ = 0; i_1_ < 8; i_1_++) {
				if ((i_0_ & 0x1) == 1)
					i_0_ = ~0x12477cdf ^ i_0_ >>> 1;
				else
					i_0_ >>>= 1;
			}
			anIntArray7[i1] = i_0_;
		}
		int i1 = 2;
		for (int i_0_ = 0; i_0_ < 32; i_0_++) {
			bitMasks[i_0_] = i1 - 1;
			i1 += i1;
		}
	}

}