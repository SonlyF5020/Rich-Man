import Main.RichMap;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RichMapTest {
	@Test
	public void should_be_start_at_position_START() {
		int startPosition = RichMap.START;
		RichMap richMap = new RichMap();
		assertThat(richMap.getOwner(startPosition), is("start"));
	}

	@Test
	public void should_be_hospital_at_position_HOSPITAL() {
		int hospitalPosition = RichMap.HOSPITAL;
		RichMap richMap = new RichMap();
		assertThat(richMap.getOwner(hospitalPosition), is("hospital"));
	}

	@Test
	public void should_be_toolHouse_at_position_TOOL_HOUSE() {
		int toolHousePosition = RichMap.TOOL_HOUSE;
		RichMap richMap = new RichMap();
		assertThat(richMap.getOwner(toolHousePosition), is("toolHouse"));
	}

	@Test
	public void should_be_giftHouse_at_position_GIFT_HOUSE() {
		int giftHousePosition = RichMap.GIFT_HOUSE;
		RichMap richMap = new RichMap();
		assertThat(richMap.getOwner(giftHousePosition), is("giftHouse"));
	}

	@Test
	public void should_be_prison_at_position_PRISON() {
		int prisonPosition = RichMap.PRISON;
		RichMap richMap = new RichMap();
		assertThat(richMap.getOwner(prisonPosition), is("prison"));
	}

	@Test
	public void should_be_magicHouse_at_position_MAGIC_HOUSE() {
		int magicHousePosition = RichMap.MAGIC_HOUSE;
		RichMap richMap = new RichMap();
		assertThat(richMap.getOwner(magicHousePosition), is("magicHouse"));
	}
	@Test
	public void should_be_inside_map_when_input_is_outside_totalMap(){
		assertThat(RichMap.initial(-1),is(69));
		assertThat(RichMap.initial(71),is(1));
		assertThat(RichMap.initial(34),is(34));
	}
	@Test
	public void should_be_no_block_at_position_6_when_used_clearTool_method_at_position_1(){
		int blockPosition=6;
		int clearCenterPosition=1;
		RichMap richMap=new RichMap();
		assertThat(richMap.isBlockHere(blockPosition),is(false));
		richMap.setBlock(blockPosition);
		assertThat(richMap.isBlockHere(blockPosition),is(true));
		richMap.clearTool(clearCenterPosition);
		assertThat(richMap.isBlockHere(blockPosition),is(false));
	}
}
